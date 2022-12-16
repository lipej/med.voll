package med.voll.api.domain.usecases.inputs.address;

public record Address(String street, String neighborhood, String zip, String city, String state, String number,
                      String complement) {

}
