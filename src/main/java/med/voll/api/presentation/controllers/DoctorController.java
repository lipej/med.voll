package med.voll.api.presentation.controllers;

import med.voll.api.domain.usecases.inputs.doctor.SingUp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @PostMapping
    public SingUp signup(@RequestBody SingUp input) {
        return input;
    }
}
