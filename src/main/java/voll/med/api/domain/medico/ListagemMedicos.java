package voll.med.api.domain.medico;

public record ListagemMedicos(Long id, String nome, String crm, Especialidade especialidade, String email) {

    public ListagemMedicos(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEspecialidade(), medico.getTelefone());
    }

}
