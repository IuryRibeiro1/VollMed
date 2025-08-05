package voll.med.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import voll.med.api.domain.medico.Especialidade;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record DadosConsulta(

        @NotNull
        Long idMedico,

        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,


        Especialidade especialidade


) {
}
