package voll.med.api.controller;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import voll.med.api.domain.medico.*;
import voll.med.api.repository.MedicoRepositorio;


import javax.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepositorio repositorio;


//  No método de cadastrar necessita devolver o código 201 (por isso o ResponseEntity),
//   precisa devolver o cabeçalho location com a URI e devolver no corpo da resposta uma representação do recurso recem criado
    @PostMapping
    @Transactional
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);
        repositorio.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemMedicos>> listar(Pageable page){
        var paginacao = repositorio.buscarMedicoAtivo(page).map(ListagemMedicos::new);
        return ResponseEntity.ok(paginacao);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarMedicos(@RequestBody @Valid AtualizarDados dados){

        var medico = repositorio.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirMedicos(@PathVariable Long id){
        var medico = repositorio.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detalharMedico(@PathVariable Long id){
        var medico = repositorio.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

}
