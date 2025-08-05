package voll.med.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voll.med.api.domain.consulta.AgendaDeConsultas;
import voll.med.api.domain.consulta.DadosConsulta;
import voll.med.api.domain.consulta.DadosDetalhadosConsulta;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultas agenda;

    @PostMapping
    public ResponseEntity realizarConsulta(@RequestBody DadosConsulta dadosConsulta){
        var dto = agenda.agendar(dadosConsulta);
        return ResponseEntity.ok(dto);
    }

}
