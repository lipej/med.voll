package med.voll.api.infra.repositories.jpa.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.infra.repositories.jpa.models.DoctorModel;

public interface IDoctorRepositoryJpa extends JpaRepository<DoctorModel, String> {
}