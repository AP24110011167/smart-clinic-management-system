
USE smart_clinic;

DELIMITER //

-- ─────────────────────────────────────────────
-- EXISTING PROCEDURES
-- ─────────────────────────────────────────────

DROP PROCEDURE IF EXISTS GetDoctorAppointments //
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

DROP PROCEDURE IF EXISTS GetAppointmentPrescriptions //
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

-- ─────────────────────────────────────────────
-- Q21: GetDailyAppointmentReportByDoctor
-- Returns all appointments for a given doctor on a given date
-- CALL GetDailyAppointmentReportByDoctor(1, '2026-10-20');
-- ─────────────────────────────────────────────

DROP PROCEDURE IF EXISTS GetDailyAppointmentReportByDoctor //
CREATE PROCEDURE GetDailyAppointmentReportByDoctor(
    IN p_doctor_id BIGINT,
    IN p_date      DATE
)
BEGIN
    SELECT
        a.appointment_id,
        d.name          AS doctor_name,
        d.speciality,
        p.name          AS patient_name,
        p.phone         AS patient_phone,
        a.appointment_time,
        a.status
    FROM Appointment a
    JOIN Doctor  d ON a.doctor_id  = d.doctor_id
    JOIN Patient p ON a.patient_id = p.patient_id
    WHERE a.doctor_id = p_doctor_id
      AND DATE(a.appointment_time) = p_date
    ORDER BY a.appointment_time;
END //

-- ─────────────────────────────────────────────
-- Q22: GetDoctorWithMostPatientsByMonth
-- Returns the doctor who had the most appointments in a given month/year
-- CALL GetDoctorWithMostPatientsByMonth(10, 2026);
-- ─────────────────────────────────────────────

DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByMonth //
CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(
    IN p_month INT,
    IN p_year  INT
)
BEGIN
    SELECT
        d.doctor_id,
        d.name          AS doctor_name,
        d.speciality,
        COUNT(a.appointment_id) AS total_appointments
    FROM Appointment a
    JOIN Doctor d ON a.doctor_id = d.doctor_id
    WHERE MONTH(a.appointment_time) = p_month
      AND YEAR(a.appointment_time)  = p_year
    GROUP BY d.doctor_id, d.name, d.speciality
    ORDER BY total_appointments DESC
    LIMIT 1;
END //

-- ─────────────────────────────────────────────
-- Q23: GetDoctorWithMostPatientsByYear
-- Returns the doctor who had the most appointments in a given year
-- CALL GetDoctorWithMostPatientsByYear(2026);
-- ─────────────────────────────────────────────

DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByYear //
CREATE PROCEDURE GetDoctorWithMostPatientsByYear(
    IN p_year INT
)
BEGIN
    SELECT
        d.doctor_id,
        d.name          AS doctor_name,
        d.speciality,
        COUNT(a.appointment_id) AS total_appointments
    FROM Appointment a
    JOIN Doctor d ON a.doctor_id = d.doctor_id
    WHERE YEAR(a.appointment_time) = p_year
    GROUP BY d.doctor_id, d.name, d.speciality
    ORDER BY total_appointments DESC
    LIMIT 1;
END //

DELIMITER ;
