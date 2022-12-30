package med.voll.api.infra.http.controllers.inputs.address;

import jakarta.validation.constraints.Pattern;

public record AddressUpdateInput(
        String street,
        String neighborhood,
        @Pattern(regexp = "\\d{8}") String zip,
        String city,
        String state,
        String number,
        String complement) {

}
