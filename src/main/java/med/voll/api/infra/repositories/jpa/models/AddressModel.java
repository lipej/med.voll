package med.voll.api.infra.repositories.jpa.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.entities.commons.Address;

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

    public void update(Address address) {
        if (address.getStreet() != null)
            this.street = address.getStreet();
        if (address.getNeighborhood() != null)
            this.neighborhood = address.getNeighborhood();
        if (address.getZip() != null)
            this.zip = address.getZip();
        if (address.getCity() != null)
            this.city = address.getCity();
        if (address.getState() != null)
            this.state = address.getState();
        if (address.getNumber() != null)
            this.number = address.getNumber();
        if (address.getComplement() != null)
            this.complement = address.getComplement();
    }
}
