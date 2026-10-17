
package com.smartclinic;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> registerPatient(
            @Valid @RequestBody Patient patient) {

        if (patientRepository.existsByEmail(patient.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Patient savedPatient = patientRepository.save(patient);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }
}
