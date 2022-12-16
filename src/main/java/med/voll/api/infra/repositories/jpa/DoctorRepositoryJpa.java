package med.voll.api.infra.repositories.jpa;

import med.voll.api.domain.entities.Address;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.infra.repositories.jpa.interfaces.IDoctorRepositoryJpa;
import med.voll.api.infra.repositories.jpa.models.DoctorModel;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class DoctorRepositoryJpa implements DoctorRepository {
    final IDoctorRepositoryJpa doctorRepo;

    public DoctorRepositoryJpa(IDoctorRepositoryJpa doctorRepo) {
        this.doctorRepo = doctorRepo;
    }

    public Doctor create(Doctor entity) {
        DoctorModel saved = this.doctorRepo.save(new DoctorModel(entity));
        Doctor result = new Doctor(
                saved.getName(),
                saved.getEmail(),
                saved.getCrm(),
                saved.getSpecialty(),
                new Address(
                        saved.getAddress().getStreet(),
                        saved.getAddress().getNeighborhood(),
                        saved.getAddress().getZip(),
                        saved.getAddress().getCity(),
                        saved.getAddress().getState(),
                        saved.getAddress().getNumber(),
                        saved.getAddress().getComplement()));
        result.setId(UUID.fromString(saved.getId()));
        return result;
    }
}
