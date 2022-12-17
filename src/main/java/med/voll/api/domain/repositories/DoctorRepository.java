package med.voll.api.domain.repositories;

import med.voll.api.domain.entities.Doctor;

public interface DoctorRepository {
    Doctor create(Doctor doctor);
}
