package med.voll.api.domain.usecases.doctor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.commons.Address;
import med.voll.api.domain.entities.enums.Specialty;
import med.voll.api.domain.exceptions.NotFoundException;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.infra.repositories.fake.DoctorRepositoryFake;
import med.voll.api.infra.repositories.fake.models.DoctorModel;

public class DeleteDoctorUseCaseTest {
    private List<DoctorModel> db;
    private Doctor doc1;
    private DoctorRepository repo;
    private DeleteDoctorUseCase useCase;

    @BeforeEach
    void createInstances() {
        db = new ArrayList<DoctorModel>();
        doc1 = new Doctor("Testing", "doctor@example.com", "+5511900000000", "123456", Specialty.CARDIOLOGY,
                new Address("Rua Luchen", "Liberdade", "11000000", "São Paulo", "SP", "123", "BLOCO 9, CASA 20"));
        db.add(new DoctorModel(doc1));
        repo = new DoctorRepositoryFake(db);
        useCase = new DeleteDoctorUseCase(repo);
    }

    @Test
    void deleteDoctor() throws NotFoundException {
        assertEquals(db.get(0).getDeletedAt(), null);
        useCase.execute(doc1.getId().toString());
        assertNotEquals(db.get(0).getDeletedAt(), null);
    }

    @Test
    void deleteDoctorFailWhenDontExists() throws NotFoundException {
        assertThrows(NotFoundException.class, () -> {
            useCase.execute(UUID.randomUUID().toString());
        });
    }
}
