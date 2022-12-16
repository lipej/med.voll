package med.voll.api.presentation.controllers.inputs.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressInput(
                @NotBlank String street,
                @NotBlank String neighborhood,
                @NotBlank @Pattern(regexp = "\\d{8}") String zip,
                @NotBlank String city,
                @NotBlank String state,
                String number,
                String complement) {

}
