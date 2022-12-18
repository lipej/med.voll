package med.voll.api.domain.usecases.doctor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.infra.repositories.fake.DoctorRepositoryFake;

public class ListUseCadeTest {
    private List<Doctor> db;
    private DoctorRepository repo;
    private ListUseCase useCase;
    private Doctor doc1;
    private Doctor doc2;

    @BeforeEach
    void setup() {
        db = new ArrayList<Doctor>();
        repo = new DoctorRepositoryFake(db);
        useCase = new ListUseCase(repo);
        doc1 = new Doctor("Testing", null, null, null, null, null);
        doc2 = new Doctor("Dr. Testing", null, null, null, null, null);
        db.add(doc1);
        db.add(doc2);
    }

    @Test
    void ListDoctors() {
        var result = useCase.execute(10, 0, "");

        assertEquals(result.getTotal(), 2);
        assertEquals(result.getData(), this.db);
        assertEquals(result.getData().get(0), doc1);
        assertEquals(result.getData().get(1), doc2);
    }

    @Test
    void ListDoctorsAndOrderByNameAndEmail() {
        var result = useCase.execute(10, 0, "name");

        assertEquals(result.getTotal(), 2);
        assertEquals(result.getData(), this.db);
        assertEquals(result.getData().get(0), doc2);
        assertEquals(result.getData().get(1), doc1);
    }

    @Test
    void ListDoctorsPaginated() {
        var result = useCase.execute(1, 0, "name");

        assertEquals(result.getTotal(), 2);
        assertEquals(result.getData().size(), 1);
        assertEquals(result.getData().get(0), doc2);
    }

    @Test
    void ListDoctorsPaginatedPageTwo() {
        var result = useCase.execute(1, 1, "name");

        assertEquals(result.getTotal(), 2);
        assertEquals(result.getData().size(), 1);
        assertEquals(result.getData().get(0), doc1);
    }
}
