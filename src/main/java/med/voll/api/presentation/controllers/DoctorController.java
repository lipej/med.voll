package med.voll.api.presentation.controllers;

import med.voll.api.domain.usecases.doctor.SignUpUseCase;
import med.voll.api.domain.usecases.inputs.doctor.SingUpInput;
import med.voll.api.domain.usecases.output.IdOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    private final SignUpUseCase signUpUseCase;

    public DoctorController(SignUpUseCase signUpUseCase) {
        this.signUpUseCase = signUpUseCase;
    }

    @PostMapping
    public IdOutput signup(@RequestBody SingUpInput input) {
        return this.signUpUseCase.execute(input);
    }
}
