package med.voll.api.domain.usecases.doctor;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.domain.usecases.BaseUseCase;
import org.springframework.stereotype.Service;

@Service
public class SignUpUseCase implements BaseUseCase<Doctor, String> {
    private final DoctorRepository doctorRepository;

    private SignUpUseCase(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public String execute(Doctor input) {
        return this.doctorRepository.create(input).getId().toString();
    }
}
