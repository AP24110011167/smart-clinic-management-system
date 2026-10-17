
USE smart_clinic;

-- Insert a sample doctor
INSERT INTO Doctor (name, email, password, speciality)
VALUES ('Dr. Ravi', 'ravi@clinic.com', 'demo123', 'General Medicine');

-- Insert doctor availability
INSERT INTO doctor_available_times (doctor_id, available_time)
VALUES (1, '10:00 AM');

-- Insert a sample patient
INSERT INTO Patient (name, email, phone, password)
VALUES ('Arun Kumar', 'arun@example.com', '9876543210', 'demo123');

-- Insert an appointment
INSERT INTO Appointment
    (doctor_id, patient_id, appointment_time, status)
VALUES
    (1, 1, '2026-10-20 10:00:00', 'BOOKED');

-- Insert a prescription
INSERT INTO Prescription
    (appointment_id, medicines, instructions)
VALUES
    (1, 'Paracetamol 500mg', 'Take as directed by the doctor');

-- Display the records
SELECT * FROM Doctor;
SELECT * FROM Patient;
SELECT * FROM Appointment;
SELECT * FROM Prescription;

-- Display appointments with doctor and patient names
SELECT
    a.appointment_id,
    d.name AS doctor_name,
    p.name AS patient_name,
    a.appointment_time,
    a.status
FROM Appointment a
JOIN Doctor d ON a.doctor_id = d.doctor_id
JOIN Patient p ON a.patient_id = p.patient_id;
