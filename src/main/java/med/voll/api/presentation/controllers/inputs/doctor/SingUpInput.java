package med.voll.api.presentation.controllers.inputs.doctor;

import med.voll.api.presentation.controllers.inputs.address.AddressInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.presentation.controllers.inputs.SpecialtyInput;

public record SingUpInput(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank @Pattern(regexp = "\\d{4,6}") String crm,
        @NotNull SpecialtyInput specialty,
        @NotNull @Valid AddressInput address) {
}
