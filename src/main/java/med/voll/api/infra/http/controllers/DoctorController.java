package med.voll.api.infra.http.controllers;

import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.commons.Address;
import med.voll.api.domain.entities.enums.Specialty;
import med.voll.api.domain.exceptions.NotFoundException;
import med.voll.api.domain.usecases.doctor.DeleteDoctorUseCase;
import med.voll.api.domain.usecases.doctor.ListDoctorUseCase;
import med.voll.api.domain.usecases.doctor.SignUpDoctorUseCase;
import med.voll.api.domain.usecases.doctor.UpdateDoctorUseCase;
import med.voll.api.infra.http.controllers.inputs.doctor.*;
import med.voll.api.infra.http.controllers.output.commons.IdOutput;
import med.voll.api.infra.http.controllers.output.commons.PaginatedOutput;
import med.voll.api.infra.http.controllers.output.doctor.DoctorOutput;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    private final SignUpDoctorUseCase signUpUseCase;
    private final ListDoctorUseCase listUseCase;
    private final UpdateDoctorUseCase updateUseCase;
    private final DeleteDoctorUseCase deleteUseCase;

    public DoctorController(SignUpDoctorUseCase signUpUseCase, ListDoctorUseCase listUseCase,
            UpdateDoctorUseCase updateUseCase, DeleteDoctorUseCase deleteUseCase) {
        this.signUpUseCase = signUpUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    @Transactional
    public IdOutput signup(@Valid @RequestBody DoctorSingUpInput input) {
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

    @PutMapping(value = "/{id}")
    public DoctorOutput update(@Valid @RequestBody DoctorUpdateInput input, @PathVariable("id") String id)
            throws NotFoundException {
        Doctor doctor = new Doctor(
                input.name(),
                null,
                input.phone(),
                null,
                null,
                new Address(
                        input.address().street(),
                        input.address().neighborhood(),
                        input.address().zip(),
                        input.address().city(),
                        input.address().state(),
                        input.address().number(),
                        input.address().complement()));

        doctor.setId(UUID.fromString(id));

        return new DoctorOutput(updateUseCase.execute(doctor));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") String id) throws NotFoundException {
        this.deleteUseCase.execute(id);
    }
}
