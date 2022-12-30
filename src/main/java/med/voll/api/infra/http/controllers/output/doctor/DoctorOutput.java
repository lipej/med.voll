package med.voll.api.infra.http.controllers.output.doctor;

import med.voll.api.domain.entities.Doctor;

public record DoctorOutput(String id, String name, String email, String crm, String specialty) {
    public DoctorOutput(Doctor doctor) {
        this(doctor.getId().toString(), doctor.getName(), doctor.getEmail(), doctor.getCrm(),
                doctor.getSpecialty().toString());
    }
}
