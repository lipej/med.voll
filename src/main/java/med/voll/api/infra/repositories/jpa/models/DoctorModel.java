package med.voll.api.infra.repositories.jpa.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.voll.api.domain.entities.Doctor;
import med.voll.api.domain.entities.enums.Specialty;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class DoctorModel {

    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private AddressModel address;

    @Setter
    private String deletedAt = null;

    public DoctorModel(Doctor data) {
        this.id = data.getId().toString();
        this.name = data.getName();
        this.email = data.getEmail();
        this.phone = data.getPhone();
        this.crm = data.getCrm();
        this.specialty = data.getSpecialty();
        this.address = new AddressModel(data.getAddress());
    }

    public void update(Doctor data) {
        if (data.getName() != null)
            this.name = data.getName();
        if (data.getPhone() != null)
            this.phone = data.getPhone();
        if (data.getAddress() != null)
            this.address.update(data.getAddress());
    }
}
