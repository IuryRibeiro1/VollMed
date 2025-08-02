package voll.med.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import voll.med.api.domain.medico.Medico;

public interface MedicoRepositorio extends JpaRepository<Medico, Long> {

    @Query("SELECT m FROM Medico m WHERE m.ativo = 1")
    Page<Medico> buscarMedicoAtivo(Pageable page);
}
