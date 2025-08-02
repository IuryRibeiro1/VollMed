package voll.med.api.domain.paciente;

import voll.med.api.domain.medico.Endereco;
import voll.med.api.domain.medico.Especialidade;
import voll.med.api.domain.medico.Medico;

public record InformacoesPaciente (Long id, String nome, String cpf,String email, String telefone, Endereco endereco) {


    public InformacoesPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail(), paciente.getTelefone(), paciente.getEndereco());
    }
}

