
package com.smartclinic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public ResponseEntity<?> getAvailableSlots(
            Long doctorId, LocalDate date, String email, String password) {

        if (email == null || email.isBlank()
                || password == null || password.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Doctor login credentials are required"));
        }

        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);

        if (doctor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Doctor not found"));
        }

        if (!email.equals(doctor.getEmail())
                || !password.equals(doctor.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid doctor credentials"));
        }

        List<String> slots = doctor.getAvailableTimes();

        return ResponseEntity.ok(Map.of(
                "doctorId", doctorId,
                "date", date.toString(),
                "availableSlots", slots == null ? List.of() : slots
        ));
    }
}
