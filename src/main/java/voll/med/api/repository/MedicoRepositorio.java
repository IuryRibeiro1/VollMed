package voll.med.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import voll.med.api.entities.Medico;

public interface MedicoRepositorio extends JpaRepository<Medico, Long> {
}
