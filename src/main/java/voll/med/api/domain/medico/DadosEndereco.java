package voll.med.api.domain.medico;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record DadosEndereco(

        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,

        @NotBlank
        String cidade,

        String complemento,

        @NotBlank
        String numero,

        @NotBlank
        String uf) {

}
