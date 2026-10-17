
package com.smartclinic;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // ── Q24: Get all doctors ──────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<?> getAllDoctors() {
        return ResponseEntity.ok(
                doctorRepository.findAll().stream()
                        .map(doctor -> Map.of(
                                "doctorId",       doctor.getDoctorId(),
                                "name",           doctor.getName(),
                                "email",          doctor.getEmail(),
                                "speciality",     doctor.getSpeciality(),
                                "availableTimes",
                                doctor.getAvailableTimes() == null
                                        ? java.util.List.of()
                                        : doctor.getAvailableTimes()
                        ))
                        .toList()
        );
    }

    // ── Q26: Search doctors by speciality and available time ──────────────────
    // Accepts both 'speciality' and 'specialty' param names, partial time match
    @GetMapping("/search")
    public ResponseEntity<?> searchDoctors(
            @RequestParam(required = false) String speciality,
            @RequestParam(value = "specialty", required = false) String specialityAlt,
            @RequestParam(required = false) String time) {

        // Accept both British and American spelling
        String spec = (speciality != null && !speciality.isBlank()) ? speciality : specialityAlt;

        List<Doctor> results;

        if (spec != null && !spec.isBlank() && time != null && !time.isBlank()) {
            results = doctorRepository.findBySpecialityAndAvailableTime(spec, time);
        } else if (spec != null && !spec.isBlank()) {
            results = doctorRepository.findBySpecialityIgnoreCase(spec);
        } else {
            results = doctorRepository.findAll();
        }

        return ResponseEntity.ok(
                results.stream()
                        .map(d -> Map.of(
                                "doctorId",       d.getDoctorId(),
                                "name",           d.getName(),
                                "email",          d.getEmail(),
                                "speciality",     d.getSpeciality(),
                                "availableTimes",
                                d.getAvailableTimes() == null
                                        ? java.util.List.of()
                                        : d.getAvailableTimes()
                        ))
                        .toList()
        );
    }

    // ── Doctor availability by date (supports both URL patterns) ─────────────
    // Pattern 1: GET /api/doctors/{doctorId}/availability?date=&token=
    // Pattern 2: GET /api/doctors/{user}/{doctorId}/availability?date=&token=
    @GetMapping("/{doctorId}/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @PathVariable Long doctorId,
            @RequestParam String date,
            @RequestParam(required = false) String token,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        String resolvedToken = token != null ? token
                : (authHeader != null && authHeader.startsWith("Bearer ") ? authHeader.substring(7) : null);

        if (resolvedToken == null || resolvedToken.isBlank()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Authentication token is required"));
        }

        if (date == null || date.isBlank()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Date is required"));
        }

        return doctorRepository.findById(doctorId)
                .<ResponseEntity<?>>map(doctor ->
                        ResponseEntity.ok(Map.of(
                                "doctorId",       doctor.getDoctorId(),
                                "date",           date,
                                "availableTimes",
                                doctor.getAvailableTimes() == null
                                        ? java.util.List.of()
                                        : doctor.getAvailableTimes()
                        )))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Doctor not found")));
    }

    // Pattern 2: GET /api/doctors/{user}/{doctorId}/availability
    @GetMapping("/{user}/{doctorId}/availability")
    public ResponseEntity<?> getDoctorAvailabilityByUser(
            @PathVariable String user,
            @PathVariable Long doctorId,
            @RequestParam String date,
            @RequestParam(required = false) String token,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        return getDoctorAvailability(doctorId, date, token, authHeader);
    }
}
