package med.voll.api.presentation.controllers.inputs.doctor;

import jakarta.validation.Valid;
import med.voll.api.presentation.controllers.inputs.address.AddressUpdateInput;

public record DoctorUpdateInput(
        String id,
        String name,
        String phone,
        @Valid AddressUpdateInput address) {
}