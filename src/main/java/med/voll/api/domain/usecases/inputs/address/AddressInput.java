package med.voll.api.domain.usecases.inputs.address;

public record AddressInput(String street, String neighborhood, String zip, String city, String state, String number,
        String complement) {

}
