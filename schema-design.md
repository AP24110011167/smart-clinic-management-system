# Smart Clinic Management System — Database Schema Design

## 1. Overview

The Smart Clinic Management System manages patients, doctors, appointments, and prescriptions using a relational database.

## 2. Entities and Attributes

### Patient
- patient_id — Primary Key
- name
- email
- phone
- age
- gender

### Doctor
- doctor_id — Primary Key
- name
- specialization
- email
- phone

### Appointment
- appointment_id — Primary Key
- patient_id — Foreign Key
- doctor_id — Foreign Key
- appointment_date
- status
- reason

### Prescription
- prescription_id — Primary Key
- appointment_id — Foreign Key
- medication
- dosage
- instructions

## 3. Relationships

- One patient can book multiple appointments.
- One doctor can handle multiple appointments.
- One appointment can have a prescription.
- Appointments connect patients and doctors.

## 4. Relational Schema

- Patient(**patient_id**, name, email, phone, age, gender)
- Doctor(**doctor_id**, name, specialization, email, phone)
- Appointment(**appointment_id**, patient_id, doctor_id, appointment_date, status, reason)
- Prescription(**prescription_id**, appointment_id, medication, dosage, instructions)

## 5. Integrity Constraints

- Primary keys uniquely identify records.
- Foreign keys maintain relationships between tables.
- Required fields should not be NULL.
- Email addresses should be unique where appropriate.

## 6. Normalization

The database separates patients, doctors, appointments, and prescriptions into individual tables to reduce data duplication and improve consistency.
