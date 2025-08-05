package voll.med.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import voll.med.api.domain.consulta.DadosConsulta;
import voll.med.api.exception.ValidacaoException;
import voll.med.api.repository.PacienteRepositorio;

@Component
public class ValidarConsultaPaciente implements ValidadorDeConsultas {

    @Autowired
    private PacienteRepositorio repositorio;

    public void validar(DadosConsulta dadosConsulta){
        var pacienteEstaAtivo = repositorio.findByAtivoId(dadosConsulta.idPaciente());
        if(pacienteEstaAtivo == 0){
            throw new ValidacaoException("Cliente não está mais ativo");
        }
    }

}
