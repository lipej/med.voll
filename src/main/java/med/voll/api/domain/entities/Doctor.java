package med.voll.api.domain.entities;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.entities.commons.Address;
import med.voll.api.domain.entities.enums.Specialty;

import java.util.UUID;

@Getter
public class Doctor {

    @Setter
    private UUID id = UUID.randomUUID();

    private String name;

    private String email;

    private String phone;

    private String crm;

    private Specialty specialty;

    private Address address;

    public Doctor(String name, String email, String phone, String crm, Specialty specialty, Address address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.crm = crm;
        this.specialty = specialty;
        this.address = address;
    }
}
