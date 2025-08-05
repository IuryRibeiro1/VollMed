package voll.med.api.domain.consulta.validacoes;

import org.springframework.stereotype.Component;
import voll.med.api.domain.consulta.DadosConsulta;
import voll.med.api.exception.ValidacaoException;

import java.time.DayOfWeek;

@Component
public class ValidarHorarioFuncionamento implements ValidadorDeConsultas {

    public void validar(DadosConsulta dadosConsulta){
        var dataConsulta = dadosConsulta.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica){
            throw new ValidacaoException("Consulta fora do padrão da clinica");
        }
    }

}
