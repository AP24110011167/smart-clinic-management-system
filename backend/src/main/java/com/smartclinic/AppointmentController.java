
package com.smartclinic;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService    appointmentService;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository     patientRepository;
    private final JwtService            jwtService;

    public AppointmentController(AppointmentService appointmentService,
                                 AppointmentRepository appointmentRepository,
                                 PatientRepository patientRepository,
                                 JwtService jwtService) {
        this.appointmentService    = appointmentService;
        this.appointmentRepository = appointmentRepository;
        this.patientRepository     = patientRepository;
        this.jwtService            = jwtService;
    }

    // ── Book an appointment ───────────────────────────────────────────────────
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment bookAppointment(@RequestBody Appointment appointment) {
        return appointmentService.bookAppointment(appointment);
    }

    // ── Doctor: get own appointments for a date ───────────────────────────────
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getDoctorAppointmentsByDate(
            @PathVariable Long doctorId,
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {
        return appointmentService.getDoctorAppointmentsByDate(doctorId, date);
    }

    // ── Patient: get own appointments (Q25) ───────────────────────────────────
    // Accepts the JWT token from the patient login response.
    // The token subject is the patient's email, which is used to look up the
    // patient record and return only that patient's appointments.
    @GetMapping("/my")
    public ResponseEntity<?> getMyAppointments(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Bearer token required"));
        }

        String token = authHeader.substring(7);
        String email;
        try {
            email = jwtService.extractEmail(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or expired token"));
        }

        Optional<Patient> patientOpt = patientRepository.findByEmail(email);
        if (patientOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Patient not found for this token"));
        }

        Long patientId = patientOpt.get().getPatientId();
        List<Appointment> appointments =
                appointmentRepository.findByPatient_PatientId(patientId);
        return ResponseEntity.ok(appointments);
    }
}
