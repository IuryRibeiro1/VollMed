package voll.med.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import voll.med.api.domain.consulta.DadosConsulta;
import voll.med.api.exception.ValidacaoException;
import voll.med.api.repository.ConsultaRepositorio;

@Component
public class ValidarDataConsultaPaciente implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepositorio repositorio;

    public void validar(DadosConsulta dadosConsulta){
        var primeiroHorario = dadosConsulta.data().withHour(7);
        var ultimoHorario = dadosConsulta.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repositorio.existsByPacienteIdAndDataBetween(dadosConsulta.idPaciente(), primeiroHorario, ultimoHorario);
        if(pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Paciente já possui uma consulta agendada neste dia");
        }
    }

}
