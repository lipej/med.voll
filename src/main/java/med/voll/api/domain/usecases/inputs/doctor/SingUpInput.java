package med.voll.api.domain.usecases.inputs.doctor;

import med.voll.api.domain.usecases.inputs.address.AddressInput;
import med.voll.api.domain.usecases.inputs.SpecialtyInput;

public record SingUpInput(String name, String email, String crm, SpecialtyInput specialty, AddressInput address) {
}
