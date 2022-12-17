package med.voll.api.infra.repositories.jpa.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.entities.Address;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressModel {
    private String street;

    private String neighborhood;

    private String zip;

    private String city;

    private String state;

    private String number;

    private String complement;

    public AddressModel(Address data) {
        this.street = data.getStreet();
        this.neighborhood = data.getNeighborhood();
        this.zip = data.getZip();
        this.city = data.getCity();
        this.state = data.getState();
        this.number = data.getNumber();
        this.complement = data.getComplement();
    }
}
