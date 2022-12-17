package med.voll.api.infra.repositories.fake;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.repositories.DoctorRepository;

import java.util.List;

public class DoctorRepositoryFake implements DoctorRepository {

    private List<Doctor> db;

    public DoctorRepositoryFake(List<Doctor> doctorList) {
        this.db = doctorList;
    }

    @Override
    public Doctor create(Doctor doctor) {
        this.db.add(doctor);

        return doctor;
    }
}
