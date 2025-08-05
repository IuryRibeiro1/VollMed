package voll.med.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import voll.med.api.domain.consulta.DadosConsulta;
import voll.med.api.repository.ConsultaRepositorio;

@Component
public class ValidarConsultaExistenteMedico implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepositorio repositorio;

    public void validar(DadosConsulta dadosConsulta){
        var medicoPossuiOutraConsultaNoHorario = repositorio.existsByMedicoIdAndData(dadosConsulta.idMedico(), dadosConsulta.data());
    }


}
