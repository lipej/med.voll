package med.voll.api.presentation.controllers.inputs.doctor;

import med.voll.api.presentation.controllers.inputs.address.AddressInput;
import med.voll.api.presentation.controllers.inputs.SpecialtyInput;

public record SingUpInput(String name, String email, String crm, SpecialtyInput specialty, AddressInput address) {
}
