# User Stories – Smart Clinic Management System

## 1. Patient User Stories

### User Story 1: Register an Account
**As a** patient,  
**I want to** register an account using my personal details,  
**So that** I can access the clinic management system.

**Acceptance Criteria:**
- The patient can enter their name, email, phone number, and password.
- The system validates the required information.
- The system prevents registration with an email address that is already registered.
- The system saves the patient details when registration is successful.

### User Story 2: Search for Doctors
**As a** patient,  
**I want to** search for doctors by name or speciality,  
**So that** I can find a suitable doctor.

**Acceptance Criteria:**
- The patient can view the available doctors.
- The patient can search using a doctor's name or speciality.
- The system displays matching doctor details.
- The system informs the patient when no matching doctors are found.

### User Story 3: Book an Appointment
**As a** patient,  
**I want to** book an appointment with a doctor,  
**So that** I can receive medical consultation.

**Acceptance Criteria:**
- The patient can select a doctor and appointment date and time.
- The system validates the appointment information.
- The system saves the appointment when the booking is successful.
- The system displays the appointment details and status.

## 2. Doctor User Stories

### User Story 4: View Appointments
**As a** doctor,  
**I want to** view my scheduled appointments,  
**So that** I can manage my daily consultations.

**Acceptance Criteria:**
- The doctor can view appointments assigned to them.
- The doctor can filter appointments by date.
- The system displays relevant appointment and patient details.
- The system does not display appointments assigned to other doctors.

### User Story 5: Manage Availability
**As a** doctor,  
**I want to** manage my available consultation times,  
**So that** patients can book appointments during suitable hours.

**Acceptance Criteria:**
- The doctor can specify available consultation times.
- The system stores the doctor's availability.
- The system displays the available times when patients search for appointments.
- The system validates the submitted availability information.

### User Story 6: Manage Prescriptions
**As a** doctor,  
**I want to** create prescriptions for appointments,  
**So that** patients can receive their prescribed medicines and instructions.

**Acceptance Criteria:**
- The doctor can associate a prescription with an appointment.
- The doctor can enter medicine details and instructions.
- The system saves the prescription information.
- The prescription is associated with the correct appointment.

## 3. Admin User Stories

### User Story 7: Manage Doctors
**As an** administrator,  
**I want to** add and manage doctor records,  
**So that** the clinic maintains accurate doctor information.

**Acceptance Criteria:**
- The administrator can add a doctor with the required details.
- The system validates the doctor information.
- The system stores the doctor record when the information is valid.
- The administrator can view the registered doctors.

### User Story 8: Manage Patient Records
**As an** administrator,  
**I want to** view and manage patient records,  
**So that** I can maintain organized clinic information.

**Acceptance Criteria:**
- The administrator can view registered patient records.
- The system displays relevant patient information.
- The system retrieves the selected patient's details correctly.
- Patient information is accessible only to authorized users.

### User Story 9: Monitor Appointments
**As an** administrator,  
**I want to** view and monitor clinic appointments,  
**So that** I can oversee appointment activity.

**Acceptance Criteria:**
- The administrator can view appointment records.
- The system displays appointment dates, times, doctors, patients, and statuses.
- The administrator can identify appointments by their relevant details.
- Appointment information is retrieved from the clinic database.

## Conclusion

The Smart Clinic Management System supports patients, doctors, and administrators through account registration, doctor search, appointment booking, availability management, prescription management, and clinic record administration. These user stories define the expected functionality and acceptance criteria for the system.
