
package com.smartclinic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AuthController(JwtService jwtService,
                          PatientRepository patientRepository,
                          DoctorRepository doctorRepository) {
        this.jwtService = jwtService;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    // ── Admin login ───────────────────────────────────────────────────────────
    @PostMapping("/login")
    public ResponseEntity<?> adminLogin(@RequestBody Map<String, String> request) {
        String email    = request.get("email");
        String password = request.get("password");

        if (email == null || email.isBlank()
                || password == null || password.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Email and password are required"));
        }

        if (!email.equals("admin@smartclinic.com")
                || !password.equals("Admin@123")) {
            return ResponseEntity.status(401)
                    .body(Map.of("error", "Invalid email or password"));
        }

        String token = jwtService.generateToken(email);
        return ResponseEntity.ok(Map.of(
                "token",     token,
                "tokenType", "Bearer",
                "role",      "ADMIN",
                "email",     email
        ));
    }

    // ── Doctor login ──────────────────────────────────────────────────────────
    @PostMapping("/doctor/login")
    public ResponseEntity<?> doctorLogin(@RequestBody Map<String, String> request) {
        String email    = request.get("email");
        String password = request.get("password");

        if (email == null || email.isBlank()
                || password == null || password.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Email and password are required"));
        }

        Optional<Doctor> doctorOpt = doctorRepository.findByEmail(email);
        if (doctorOpt.isEmpty()
                || !doctorOpt.get().getPassword().equals(password)) {
            return ResponseEntity.status(401)
                    .body(Map.of("error", "Invalid doctor credentials"));
        }

        Doctor doctor = doctorOpt.get();
        String token  = jwtService.generateToken(email);
        return ResponseEntity.ok(Map.of(
                "token",      token,
                "tokenType",  "Bearer",
                "role",       "DOCTOR",
                "doctorId",   doctor.getDoctorId(),
                "name",       doctor.getName(),
                "email",      doctor.getEmail(),
                "speciality", doctor.getSpeciality()
        ));
    }

    // ── Patient login ─────────────────────────────────────────────────────────
    @PostMapping("/patient/login")
    public ResponseEntity<?> patientLogin(@RequestBody Map<String, String> request) {
        String email    = request.get("email");
        String password = request.get("password");

        if (email == null || email.isBlank()
                || password == null || password.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Email and password are required"));
        }

        Optional<Patient> patientOpt = patientRepository.findByEmail(email);
        if (patientOpt.isEmpty()
                || !patientOpt.get().getPassword().equals(password)) {
            return ResponseEntity.status(401)
                    .body(Map.of("error", "Invalid patient credentials"));
        }

        Patient patient = patientOpt.get();
        String token    = jwtService.generateToken(email);
        return ResponseEntity.ok(Map.of(
                "token",     token,
                "tokenType", "Bearer",
                "role",      "PATIENT",
                "patientId", patient.getPatientId(),
                "name",      patient.getName(),
                "email",     patient.getEmail()
        ));
    }
}
