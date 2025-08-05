package voll.med.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import voll.med.api.domain.consulta.DadosConsulta;
import voll.med.api.exception.ValidacaoException;
import voll.med.api.repository.MedicoRepositorio;

@Component
public class ValidarMedicoAtivo implements ValidadorDeConsultas {

    @Autowired
    private MedicoRepositorio repositorio;

    public void validar(DadosConsulta dadosConsulta){
        if(dadosConsulta.idMedico() == null){
            return;
        }

        var medicoAtivo = repositorio.findByAtivoId(dadosConsulta.idMedico());
        if(medicoAtivo == 0){
            throw new ValidacaoException("Consulta não pode ser agendada com o médico");
        }
    }

}
