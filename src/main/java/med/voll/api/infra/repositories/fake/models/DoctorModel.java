package med.voll.api.infra.repositories.fake.models;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import med.voll.api.domain.entities.Doctor;

public class DoctorModel extends Doctor {

    @Getter
    @Setter
    private String deletedAt;

    public DoctorModel(Doctor doc) {
        super(doc.getName(), doc.getEmail(), doc.getPhone(), doc.getCrm(), doc.getSpecialty(), doc.getAddress());

        this.setId(doc.getId());
    }

}
