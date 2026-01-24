package com.MatheusLefa.SpringCalculadora.repository;

import com.MatheusLefa.SpringCalculadora.domain.Entity.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

   Optional<Usuario> findByLogin(String login);

    boolean existsByEmail(String email);
}
