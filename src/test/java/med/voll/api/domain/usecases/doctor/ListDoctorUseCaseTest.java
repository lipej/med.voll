package med.voll.api.domain.usecases.doctor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.infra.repositories.fake.DoctorRepositoryFake;
import med.voll.api.infra.repositories.fake.models.DoctorModel;

public class ListDoctorUseCaseTest {
    private List<DoctorModel> db;
    private DoctorRepository repo;
    private ListDoctorUseCase useCase;
    private Doctor doc1;
    private Doctor doc2;

    @BeforeEach
    void setup() {
        db = new ArrayList<DoctorModel>();
        repo = new DoctorRepositoryFake(db);
        useCase = new ListDoctorUseCase(repo);
        doc1 = new Doctor("Testing", null, null, null, null, null);
        doc2 = new Doctor("Dr. Testing", null, null, null, null, null);
        db.add(new DoctorModel(doc1));
        db.add(new DoctorModel(doc2));
    }

    @Test
    void ListDoctors() {
        var result = useCase.execute(10, 0, "");

        assertEquals(result.getTotal(), 2);
        assertEquals(result.getData().get(0).getName(), doc1.getName());
        assertEquals(result.getData().get(1).getName(), doc2.getName());
    }

    @Test
    void ListDoctorsAndOrderByNameAndEmail() {
        var result = useCase.execute(10, 0, "name");

        assertEquals(result.getTotal(), 2);
        assertEquals(result.getData().get(0).getName(), doc2.getName());
        assertEquals(result.getData().get(1).getName(), doc1.getName());
    }

    @Test
    void ListDoctorsPaginated() {
        var result = useCase.execute(1, 0, "name");

        assertEquals(result.getTotal(), 2);
        assertEquals(result.getData().size(), 1);
        assertEquals(result.getData().get(0).getName(), doc2.getName());
    }

    @Test
    void ListDoctorsPaginatedPageTwo() {
        var result = useCase.execute(1, 1, "name");

        assertEquals(result.getTotal(), 2);
        assertEquals(result.getData().size(), 1);
        assertEquals(result.getData().get(0).getName(), doc1.getName());
    }
}
