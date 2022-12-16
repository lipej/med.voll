package med.voll.api.domain.usecases.inputs.doctor;


import med.voll.api.domain.usecases.inputs.address.Address;
import med.voll.api.domain.usecases.inputs.Specialty;

public record SingUp(String name, String email, String crm, Specialty specialty, Address address) {
}
