package voll.med.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import voll.med.api.domain.medico.ListagemMedicos;
import voll.med.api.domain.paciente.DadosCadastroPaciente;
import voll.med.api.domain.paciente.InformacoesPaciente;
import voll.med.api.domain.paciente.Paciente;
import voll.med.api.repository.PacienteRepositorio;

import javax.validation.Valid;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepositorio repositorio;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        var pacienteCadastro = new Paciente(dados);
        repositorio.save(pacienteCadastro);

        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(pacienteCadastro.getId()).toUri();

        return ResponseEntity.created(uri).body(new InformacoesPaciente(pacienteCadastro));
    }

    @GetMapping
    public ResponseEntity<Page<InformacoesPaciente>> listar(Pageable page){
        var paginacao = repositorio.findById(page).map(InformacoesPaciente::new);
        return ResponseEntity.ok(paginacao);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirPacientes(@PathVariable Long id){
        var paciente = repositorio.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }

}
