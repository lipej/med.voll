package med.voll.api.domain.entities.commons;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private String street;

    private String neighborhood;

    private String zip;

    private String city;

    private String state;

    private String number;

    private String complement;

    public Address(String street, String neighborhood, String zip, String city, String state, String number,
            String complement) {
        this.street = street;
        this.neighborhood = neighborhood;
        this.zip = zip;
        this.city = city;
        this.state = state;
        this.number = number;
        this.complement = complement;
    }
}
