package med.voll.api.infra.repositories.fake;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.commons.Page;
import med.voll.api.domain.exceptions.NotFoundException;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.infra.repositories.fake.models.DoctorModel;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class DoctorRepositoryFake implements DoctorRepository {

    private List<DoctorModel> db;

    public DoctorRepositoryFake(List<DoctorModel> doctorList) {
        this.db = doctorList;
    }

    @Override
    public Doctor create(Doctor doctor) {
        var model = new DoctorModel(doctor);
        this.db.add(model);

        return doctor;
    }

    @Override
    public Page<List<Doctor>> listAll(int limit, int page, String order) {
        Comparator<Doctor> comparator;

        if (order.contains("name")) {
            comparator = Comparator.comparing(Doctor::getName);
            this.db.sort(comparator);
        }

        return new Page<List<Doctor>>(this.db.size(),
                this.db.stream().filter(it -> it.getDeletedAt() == null).skip(page * limit).limit(limit)
                        .map(this::toEntity).toList());
    }

    @Override
    public Doctor update(Doctor doctor) throws NotFoundException {
        DoctorModel doctorToUpdate = this.db.stream()
                .filter(it -> doctor.getId().equals(it.getId()))
                .findAny().orElse(null);

        if (doctorToUpdate == null)
            throw new NotFoundException();

        if (doctorToUpdate.getDeletedAt() != null)
            return this.toEntity(doctorToUpdate);

        if (doctor.getName() != null)
            doctorToUpdate.setName(doctor.getName());

        if (doctor.getPhone() != null)
            doctorToUpdate.setPhone(doctor.getPhone());

        if (doctor.getAddress() != null) {
            var newAddress = doctor.getAddress();

            if (newAddress.getStreet() != null)
                doctorToUpdate.getAddress().setStreet(doctor.getAddress().getStreet());

            if (newAddress.getNeighborhood() != null)
                doctorToUpdate.getAddress().setNeighborhood(newAddress.getNeighborhood());

            if (newAddress.getZip() != null)
                doctorToUpdate.getAddress().setZip(newAddress.getZip());

            if (newAddress.getCity() != null)
                doctorToUpdate.getAddress().setCity(newAddress.getCity());

            if (newAddress.getState() != null)
                doctorToUpdate.getAddress().setState(newAddress.getState());

            if (newAddress.getNumber() != null)
                doctorToUpdate.getAddress().setNumber(newAddress.getNumber());

            if (newAddress.getComplement() != null)
                doctorToUpdate.getAddress().setComplement(newAddress.getComplement());
        }

        return this.toEntity(doctorToUpdate);
    }

    public void delete(String id) throws NotFoundException {
        DoctorModel doctorToUpdate = this.db.stream()
                .filter(it -> id.equals(it.getId().toString()))
                .findAny().orElse(null);

        if (doctorToUpdate == null)
            throw new NotFoundException();

        if (doctorToUpdate.getDeletedAt() == null)
            doctorToUpdate.setDeletedAt(LocalDate.now().toString());
    }

    private Doctor toEntity(DoctorModel model) {
        var docFromDb = new Doctor(model.getName(), model.getEmail(), model.getPhone(), model.getCrm(),
                model.getSpecialty(), model.getAddress());

        docFromDb.setId(model.getId());

        return docFromDb;
    }

}
