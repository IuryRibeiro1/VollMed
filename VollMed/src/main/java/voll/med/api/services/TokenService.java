package voll.med.api.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;
import voll.med.api.domain.usuario.Usuarios;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public static final String usuarioToken = System.getenv("JWT_TOKEN");

    public String gerarToken(Usuarios usuarios) {
        try {
            var algoritimo = Algorithm.HMAC256(usuarioToken);
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(usuarios.getUsuario())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritimo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token" , exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algoritimo = Algorithm.HMAC256(usuarioToken);
            return JWT.require(algoritimo)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token Inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
            return (LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")));

    }

}
