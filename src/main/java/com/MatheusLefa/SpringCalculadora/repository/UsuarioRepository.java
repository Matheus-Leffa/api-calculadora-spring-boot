package com.MatheusLefa.SpringCalculadora.repository;

import com.MatheusLefa.SpringCalculadora.domain.Entity.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

   Optional<Usuario> findByLogin(String login);

    boolean existsByEmail(String email);
}
