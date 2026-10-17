
package com.smartclinic;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "Prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prescription_id")
    private Long prescriptionId;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    @NotNull
    private Appointment appointment;

    @NotBlank
    @Column(name = "medicines", nullable = false, columnDefinition = "TEXT")
    private String medicines;

    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;

    @Column(name = "prescribed_at", insertable = false, updatable = false)
    private LocalDateTime prescribedAt;

    public Prescription() {}

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public LocalDateTime getPrescribedAt() {
        return prescribedAt;
    }
}
