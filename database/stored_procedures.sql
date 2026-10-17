
USE smart_clinic;

DELIMITER //

-- Get all appointments for a particular doctor
CREATE PROCEDURE GetDoctorAppointments(IN p_doctor_id BIGINT)
BEGIN
    SELECT
        a.appointment_id,
        d.name AS doctor_name,
        p.name AS patient_name,
        a.appointment_time,
        a.status
    FROM Appointment a
    JOIN Doctor d ON a.doctor_id = d.doctor_id
    JOIN Patient p ON a.patient_id = p.patient_id
    WHERE a.doctor_id = p_doctor_id;
END //

-- Get all prescriptions for a particular appointment
CREATE PROCEDURE GetAppointmentPrescriptions(IN p_appointment_id BIGINT)
BEGIN
    SELECT
        prescription_id,
        appointment_id,
        medicines,
        instructions,
        prescribed_at
    FROM Prescription
    WHERE appointment_id = p_appointment_id;
END //

DELIMITER ;

-- Example calls (run after creating the procedures)
CALL GetDoctorAppointments(1);
CALL GetAppointmentPrescriptions(1);
