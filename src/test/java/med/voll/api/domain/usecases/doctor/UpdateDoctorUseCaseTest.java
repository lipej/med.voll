package med.voll.api.domain.usecases.doctor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.commons.Address;
import med.voll.api.domain.entities.enums.Specialty;
import med.voll.api.domain.exceptions.NotFoundException;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.infra.repositories.fake.DoctorRepositoryFake;
import med.voll.api.infra.repositories.fake.models.DoctorModel;

public class UpdateDoctorUseCaseTest {
    private List<DoctorModel> db;
    private Doctor doc1;
    private DoctorRepository repo;
    private UpdateDoctorUseCase useCase;

    @BeforeEach
    void createInstances() {
        db = new ArrayList<DoctorModel>();
        doc1 = new Doctor("Testing", "doctor@example.com", "+5511900000000", "123456", Specialty.CARDIOLOGY,
                new Address("Rua Luchen", "Liberdade", "11000000", "São Paulo", "SP", "123", "BLOCO 9, CASA 20"));
        db.add(new DoctorModel(doc1));
        repo = new DoctorRepositoryFake(db);
        useCase = new UpdateDoctorUseCase(repo);
    }

    @Test
    void updateDoctorName() throws NotFoundException {
        var newName = "Almeida";
        var updatedDoctor = new Doctor(newName, null, null, null, null, null);
        updatedDoctor.setId(doc1.getId());

        useCase.execute(updatedDoctor);

        assertEquals(db.get(0).getName(), newName);
    }

    @Test
    void updateDoctorPhone() throws NotFoundException {
        var newPhone = "+5541900000000";
        var updatedDoctor = new Doctor(null, null, newPhone, null, null, null);
        updatedDoctor.setId(doc1.getId());

        useCase.execute(updatedDoctor);

        assertEquals(db.get(0).getPhone(), newPhone);
    }

    @Test
    void updateDoctorAddress() throws NotFoundException {
        var newAddress = new Address("Rua Braga", "Centro", "11111000", "São Paulo", "SP", "12", "FRENTE");
        var updatedDoctor = new Doctor(null, null, null, null, null, newAddress);
        updatedDoctor.setId(doc1.getId());

        useCase.execute(updatedDoctor);

        var oldAddress = db.get(0).getAddress();

        assertEquals(oldAddress.getStreet(), newAddress.getStreet());
        assertEquals(oldAddress.getNeighborhood(), newAddress.getNeighborhood());
        assertEquals(oldAddress.getZip(), newAddress.getZip());
        assertEquals(oldAddress.getCity(), newAddress.getCity());
        assertEquals(oldAddress.getState(), newAddress.getState());
        assertEquals(oldAddress.getNumber(), newAddress.getNumber());
        assertEquals(oldAddress.getComplement(), newAddress.getComplement());
    }

    @Test
    void updateDoctorFailWhenDontExists() throws NotFoundException {
        var updatedDoctor = new Doctor(null, null, null, null, null, null);

        assertThrows(NotFoundException.class, () -> {
            useCase.execute(updatedDoctor);
        });
    }
}
