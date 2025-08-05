package voll.med.api.domain.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import voll.med.api.domain.consulta.validacoes.ValidadorDeConsultas;
import voll.med.api.domain.medico.Medico;
import voll.med.api.exception.ValidacaoException;
import voll.med.api.repository.ConsultaRepositorio;
import voll.med.api.repository.MedicoRepositorio;
import voll.med.api.repository.PacienteRepositorio;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepositorio consultarepositorio;

    @Autowired
    private MedicoRepositorio medicoRepositorio;

    @Autowired
    private PacienteRepositorio pacienteRepositorio;

    @Autowired
    private List<ValidadorDeConsultas> validadores;

    public DadosDetalhadosConsulta agendar(DadosConsulta dadosConsulta){
        if(!pacienteRepositorio.existsById(dadosConsulta.idPaciente())){
            throw new ValidacaoException("Id do paciente não existe");
        }

        if(dadosConsulta.idMedico() != null && !medicoRepositorio.existsById(dadosConsulta.idMedico())){
            throw new ValidacaoException("Id do medico não existe");
        }

        validadores.forEach(v -> v.validar(dadosConsulta));

        var paciente = pacienteRepositorio.getReferenceById(dadosConsulta.idPaciente());
        var medico = escolherMedicos(dadosConsulta);
        var consulta = new Consulta(null , medico, paciente , dadosConsulta.data());
        consultarepositorio.save(consulta);

        return new DadosDetalhadosConsulta(consulta);
    }


    private Medico escolherMedicos(DadosConsulta dadosConsulta) {
        if(dadosConsulta.idMedico() != null){
            return medicoRepositorio.getReferenceById(dadosConsulta.idMedico());
        }
        else if (dadosConsulta.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatória");

        }
        return medicoRepositorio.escolherMedicoAleatorioNaDataDisponivel(dadosConsulta.especialidade() , dadosConsulta.data());
    }

}
