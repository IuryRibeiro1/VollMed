package voll.med.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import voll.med.api.domain.consulta.Consulta;
import voll.med.api.domain.medico.Endereco;

import java.util.List;


@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    @Embedded
    private Endereco endereco;

    @OneToMany
    private List<Consulta> consulta;

    private int ativo;


    public Paciente(DadosCadastroPaciente paciente){
        this.nome = paciente.nome();
        this.cpf = paciente.cpf();
        this.email = paciente.email();
        this.telefone = paciente.telefone();
        this.endereco = new Endereco(paciente.endereco());
    }

        public void excluir(){
        this.ativo = 0;
        }


}
