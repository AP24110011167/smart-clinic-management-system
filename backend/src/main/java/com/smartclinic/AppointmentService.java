
package com.smartclinic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Transactional
    public Appointment bookAppointment(Appointment appointment) {
        if (appointment == null ||
                appointment.getDoctor() == null ||
                appointment.getPatient() == null ||
                appointment.getAppointmentTime() == null) {
            throw new IllegalArgumentException(
                    "Doctor, patient, and appointment time are required");
        }

        if (appointment.getStatus() == null ||
                appointment.getStatus().isBlank()) {
            appointment.setStatus("BOOKED");
        }

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getDoctorAppointmentsByDate(
            Long doctorId, LocalDate date) {

        return appointmentRepository
                .findByDoctor_DoctorIdAndAppointmentTimeBetween(
                        doctorId,
                        date.atStartOfDay(),
                        date.plusDays(1).atStartOfDay().minusNanos(1));
    }
}
