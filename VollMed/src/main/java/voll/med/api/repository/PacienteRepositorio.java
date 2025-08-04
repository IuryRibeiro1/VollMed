package voll.med.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import voll.med.api.domain.paciente.Paciente;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {

    @Query("SELECT p FROM Paciente p WHERE p.id != 0")
    Page<Paciente> findById(Pageable page);
}
