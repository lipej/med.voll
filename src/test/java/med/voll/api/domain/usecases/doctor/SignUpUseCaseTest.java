package med.voll.api.domain.usecases.doctor;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.commons.Address;
import med.voll.api.domain.entities.enums.Specialty;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.infra.repositories.fake.DoctorRepositoryFake;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SignUpUseCaseTest {
    private List<Doctor> db;
    private DoctorRepository repo;
    private SignUpUseCase useCase;

    @BeforeEach
    void createInstances() {
        db = new ArrayList<Doctor>();
        repo = new DoctorRepositoryFake(db);
        useCase = new SignUpUseCase(repo);
    }

    @Test
    void signUpDoctor() {
        var address = new Address("Rua José Carlos", "Pinheiros", "12322673", "São Paulo", "SP", "123", null);
        var doctor = new Doctor("Tester Dr.", "tester@example.com", "+5541900000000", "123456", Specialty.CARDIOLOGY,
                address);

        var result = useCase.execute(doctor);

        assertEquals(result instanceof String, true);
        assertEquals(db.size(), 1);
    }
}
