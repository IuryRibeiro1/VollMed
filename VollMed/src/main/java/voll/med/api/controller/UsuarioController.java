package voll.med.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import voll.med.api.domain.usuario.DadosLogin;
import voll.med.api.domain.usuario.DadosToken;
import voll.med.api.domain.usuario.Usuarios;
import voll.med.api.services.TokenService;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")

public class UsuarioController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosLogin dadosLogin){
        var token = new UsernamePasswordAuthenticationToken(dadosLogin.login(), dadosLogin.senha());
        var authentication = authenticationManager.authenticate(token);

        var tokenUsuario = tokenService.gerarToken((Usuarios) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosToken(tokenUsuario));
    }

}
