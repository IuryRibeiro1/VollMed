package voll.med.api.domain.medico;

import javax.validation.constraints.NotNull;

public record AtualizarDados(

        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco dadosEndereco) {
}
