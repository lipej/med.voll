package med.voll.api.domain.repositories;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.commons.Page;

import java.util.List;
import med.voll.api.domain.exceptions.NotFoundException;

public interface DoctorRepository {
    Doctor create(Doctor doctor);

    Page<List<Doctor>> listAll(int limit, int page, String order);

    Doctor update(Doctor id) throws NotFoundException;
}
