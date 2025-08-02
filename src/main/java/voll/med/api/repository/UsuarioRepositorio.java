package voll.med.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import voll.med.api.domain.usuario.Usuarios;

public interface UsuarioRepositorio extends JpaRepository<Usuarios, Long> {

    UserDetails findByUsuario(String login);
}
