package voll.med.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import voll.med.api.domain.medico.Especialidade;
import voll.med.api.domain.medico.Medico;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public interface MedicoRepositorio extends JpaRepository<Medico, Long> {

    @Query("SELECT m FROM Medico m WHERE m.ativo = 1")
    Page<Medico> buscarMedicoAtivo(Pageable page);

    @Query("""
            select m from Medico m
            where
            m.ativo = 1
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.medico.id from Consulta c
                where
                c.data = data
            )
            order by rand()
            limit 1
            """)
    Medico escolherMedicoAleatorioNaDataDisponivel(Especialidade especialidade, @NotNull @Future LocalDateTime data);


    @Query("""
            select m.ativo
            from Medico m
            where
            m.id = :id
            """)
    Integer findByAtivoId(Long id);
}
