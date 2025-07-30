package voll.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import voll.med.api.entities.DadosCadastroMedico;
import voll.med.api.entities.Medico;
import voll.med.api.repository.MedicoRepositorio;

import javax.validation.Valid;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepositorio repositorio;

    @PostMapping
    public void retornarMedico(@RequestBody @Valid DadosCadastroMedico dados){
        repositorio.save(new Medico(dados));
    }

}
