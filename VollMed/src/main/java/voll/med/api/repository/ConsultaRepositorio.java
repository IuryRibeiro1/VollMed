package voll.med.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import voll.med.api.domain.consulta.Consulta;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public interface ConsultaRepositorio extends JpaRepository<Consulta, Long> {



    Boolean existsByMedicoIdAndData(Long id, @NotNull @Future LocalDateTime data);


    Boolean existsByPacienteIdAndDataBetween(@NotNull Long id, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
