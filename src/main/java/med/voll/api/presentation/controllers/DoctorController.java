package med.voll.api.presentation.controllers;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.commons.Address;
import med.voll.api.domain.entities.enums.Specialty;
import med.voll.api.domain.usecases.doctor.ListUseCase;
import med.voll.api.domain.usecases.doctor.SignUpUseCase;
import med.voll.api.presentation.controllers.inputs.doctor.*;
import med.voll.api.presentation.controllers.output.commons.IdOutput;
import med.voll.api.presentation.controllers.output.commons.PaginatedOutput;
import med.voll.api.presentation.controllers.output.doctor.DoctorOutput;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    private final SignUpUseCase signUpUseCase;
    private final ListUseCase listUseCase;

    public DoctorController(SignUpUseCase signUpUseCase, ListUseCase listUseCase) {
        this.signUpUseCase = signUpUseCase;
        this.listUseCase = listUseCase;
    }

    @PostMapping
    @Transactional
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

    @GetMapping
    public PaginatedOutput<List<DoctorOutput>> list(
            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "order", required = false, defaultValue = "email") String order) {
        var result = listUseCase.execute(limit, page, order);

        return new PaginatedOutput<List<DoctorOutput>>(page, limit, result.getTotal(),
                result.getData().stream().map(DoctorOutput::new).toList());
    }
}
