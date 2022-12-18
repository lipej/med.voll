package med.voll.api.domain.repositories;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.commons.Page;

import java.util.List;

public interface DoctorRepository {
    Doctor create(Doctor doctor);

    Page<List<Doctor>> listAll(int limit, int page, String order);
}
