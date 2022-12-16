package med.voll.api.domain.usecases.doctor;

import med.voll.api.domain.entities.Address;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.enums.Specialty;
import med.voll.api.domain.repositories.DoctorRepository;
import med.voll.api.domain.usecases.BaseUseCase;
import med.voll.api.domain.usecases.inputs.doctor.SingUpInput;
import med.voll.api.domain.usecases.output.IdOutput;
import org.springframework.stereotype.Service;

@Service
public class SignUpUseCase implements BaseUseCase<SingUpInput, IdOutput> {
    private final DoctorRepository doctorRepository;

    private SignUpUseCase(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public IdOutput execute(SingUpInput input) {

        Doctor doctor = new Doctor(
                input.name(),
                input.email(),
                input.crm(),
                Specialty.valueOf(input.specialty().toString()),
                new Address(
                        input.address().street(),
                        input.address().neighborhood(),
                        input.address().zip(),
                        input.address().city(),
                        input.address().state(),
                        input.address().number(),
                        input.address().complement()));

        return new IdOutput(this.doctorRepository.create(doctor).getId().toString());
    }
}
