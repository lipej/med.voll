package med.voll.api.presentation.controllers;

import med.voll.api.domain.entities.Address;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.enums.Specialty;
import med.voll.api.domain.usecases.doctor.SignUpUseCase;
import med.voll.api.presentation.controllers.inputs.doctor.*;
import med.voll.api.presentation.controllers.output.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    private final SignUpUseCase signUpUseCase;

    public DoctorController(SignUpUseCase signUpUseCase) {
        this.signUpUseCase = signUpUseCase;
    }

    @PostMapping
    public IdOutput signup(@Valid @RequestBody SingUpInput input) {
        Doctor doctor = new Doctor(
                input.name(),
                input.email(),
                input.phone(),
                input.crm(),
                Specialty.valueOf(input.specialty().toString().toUpperCase()),
                new Address(
                        input.address().street(),
                        input.address().neighborhood(),
                        input.address().zip(),
                        input.address().city(),
                        input.address().state(),
                        input.address().number(),
                        input.address().complement()));

        return new IdOutput(this.signUpUseCase.execute(doctor));
    }
}
