package voll.med.api.domain.consulta.validacoes;

import org.springframework.stereotype.Component;
import voll.med.api.domain.consulta.DadosConsulta;
import voll.med.api.exception.ValidacaoException;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioAntecedencia implements ValidadorDeConsultas {

    public void validar(DadosConsulta dadosConsulta){
        var dataConsulta = dadosConsulta.data();
        var agora = LocalDateTime.now();
        var diferencaAgendamento = Duration.between(agora , dataConsulta).toMinutes();

        if(diferencaAgendamento < 30){
            throw new ValidacaoException("A consulta deve ser agendada 30 minutos antes");
        }

    }

}
