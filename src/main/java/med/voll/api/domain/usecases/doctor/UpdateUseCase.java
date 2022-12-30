package med.voll.api.domain.usecases.doctor;

import org.springframework.stereotype.Service;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.exceptions.NotFoundException;
import med.voll.api.domain.repositories.DoctorRepository;

@Service
public class UpdateUseCase {
    private final DoctorRepository doctorRepository;

    public UpdateUseCase(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor execute(Doctor input) throws NotFoundException {
        return this.doctorRepository.update(input);
    }
}
