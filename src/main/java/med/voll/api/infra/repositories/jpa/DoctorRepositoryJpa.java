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

        return this.toEntity(saved);
    }

    private Doctor toEntity(DoctorModel model) {
        Doctor entity = new Doctor(
                model.getName(),
                model.getEmail(),
                model.getPhone(),
                model.getCrm(),
                model.getSpecialty(),
                new Address(
                        model.getAddress().getStreet(),
                        model.getAddress().getNeighborhood(),
                        model.getAddress().getZip(),
                        model.getAddress().getCity(),
                        model.getAddress().getState(),
                        model.getAddress().getNumber(),
                        model.getAddress().getComplement()));

        entity.setId(UUID.fromString(model.getId()));

        return entity;
    }
}
