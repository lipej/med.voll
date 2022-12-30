package med.voll.api.infra.repositories.jpa;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.commons.Address;
import med.voll.api.domain.entities.commons.Page;
import med.voll.api.domain.exceptions.NotFoundException;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.infra.repositories.jpa.interfaces.IDoctorRepositoryJpa;
import med.voll.api.infra.repositories.jpa.models.DoctorModel;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;
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

    @Override
    public Page<List<Doctor>> listAll(int limit, int page, String order) {
        Pageable pageable = PageRequest.of(page, limit, Sort.Direction.ASC, order.split(","));
        var doctors = this.doctorRepo
                .findByDeletedAtIsNull(pageable);
        return new Page<List<Doctor>>(
                doctors.size(),
                doctors.stream().map(this::toEntity).toList());
    }

    public Doctor update(Doctor doctor) throws NotFoundException {
        try {
            DoctorModel doctorToUpdate = this.doctorRepo.getReferenceById(doctor.getId().toString());

            if (doctorToUpdate.getDeletedAt() == null) {
                doctorToUpdate.update(doctor);
                this.doctorRepo.save(doctorToUpdate);
            }

            return this.toEntity(doctorToUpdate);
        } catch (Exception e) {
            if (e instanceof EntityNotFoundException) {
                throw new NotFoundException();
            }

            throw e;
        }
    }

    public void delete(String id) throws NotFoundException {
        try {
            DoctorModel doctorToUpdate = this.doctorRepo.getReferenceById(id);

            if (doctorToUpdate.getDeletedAt() == null) {
                doctorToUpdate.setDeletedAt(LocalDate.now().toString());
                this.doctorRepo.save(doctorToUpdate);
            }

        } catch (Exception e) {
            if (e instanceof EntityNotFoundException) {
                throw new NotFoundException();
            }

            throw e;
        }
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
