package voll.med.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import voll.med.api.domain.consulta.Consulta;

import javax.validation.Valid;
import java.util.List;

@Entity
@Table(name = "medicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    @OneToMany
    private List<Consulta> consulta;

    private int ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = 1;
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());


    }

    public void atualizarInformacoes(@Valid AtualizarDados dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.dadosEndereco() != null){
            this.endereco.atualizarInformacoesEndereco(dados.dadosEndereco());
        }

    }

    public void excluir() {
        this.ativo = 0;
    }
}
