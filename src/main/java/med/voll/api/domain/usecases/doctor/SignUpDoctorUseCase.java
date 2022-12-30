package med.voll.api.domain.usecases.doctor;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.repositories.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class SignUpDoctorUseCase {
    private final DoctorRepository doctorRepository;

    public SignUpDoctorUseCase(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public String execute(Doctor input) {
        return this.doctorRepository.create(input).getId().toString();
    }
}
