package med.voll.api.domain.usecases.doctor;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.commons.Page;
import med.voll.api.domain.repositories.DoctorRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUseCase {
    private final DoctorRepository doctorRepository;

    public ListUseCase(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Page<List<Doctor>> execute(int limit, int page, String order) {
        return doctorRepository.listAll(limit, page, order);
    }

}
