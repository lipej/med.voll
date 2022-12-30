package med.voll.api.domain.usecases.doctor;

import org.springframework.stereotype.Service;

import med.voll.api.domain.exceptions.NotFoundException;
import med.voll.api.domain.repositories.DoctorRepository;

@Service
public class DeleteDoctorUseCase {
    private final DoctorRepository doctorRepository;

    public DeleteDoctorUseCase(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void execute(String id) throws NotFoundException {
        this.doctorRepository.delete(id);
    }
}
