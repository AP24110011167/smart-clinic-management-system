
package com.smartclinic;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing prescriptions.
 * Doctors can create prescriptions; all roles can view them.
 */
@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;
    private final JwtService jwtService;

    public PrescriptionController(PrescriptionService prescriptionService,
                                   JwtService jwtService) {
        this.prescriptionService = prescriptionService;
        this.jwtService = jwtService;
    }

    /** GET /api/prescriptions — retrieve all prescriptions */
    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    /**
     * POST /api/prescriptions/{token}
     * Creates a new prescription. Requires a valid JWT token as a path variable.
     * The request body is validated with @Valid before processing.
     */
    @PostMapping("/{token}")
    public ResponseEntity<?> createPrescription(
            @PathVariable String token,
            @Valid @RequestBody Prescription prescription) {

        // Validate the token
        try {
            String email = jwtService.extractEmail(token);
            if (email == null || email.isBlank()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Invalid or expired token"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or expired token"));
        }

        Prescription saved = prescriptionService.createPrescription(prescription);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
