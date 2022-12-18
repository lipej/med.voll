package med.voll.api.presentation.controllers.output.doctor;

import med.voll.api.domain.entities.Doctor;

public record DoctorOutput(String name, String email, String crm, String specialty) {
    public DoctorOutput(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty().toString());
    }
}
