package med.voll.api.infra.repositories.fake;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.commons.Page;
import med.voll.api.domain.exceptions.NotFoundException;
import med.voll.api.domain.repositories.DoctorRepository;

import java.util.Comparator;
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

    @Override
    public Page<List<Doctor>> listAll(int limit, int page, String order) {
        Comparator<Doctor> comparator;

        if (order.contains("name")) {
            comparator = Comparator.comparing(Doctor::getName);
            this.db.sort(comparator);
        }

        return new Page<List<Doctor>>(this.db.size(), this.db.stream().skip(page * limit).limit(limit).toList());
    }

    @Override
    public Doctor update(Doctor doctor) throws NotFoundException {
        Doctor doctorToUpdate = this.db.stream().filter(it -> doctor.getId().equals(it.getId()))
                .findAny().orElse(null);

        if (doctorToUpdate == null)
            throw new NotFoundException();

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

        return doctorToUpdate;
    }

}
