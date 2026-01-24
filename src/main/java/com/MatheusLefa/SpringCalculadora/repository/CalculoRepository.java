package com.MatheusLefa.SpringCalculadora.repository;

import com.MatheusLefa.SpringCalculadora.domain.Entity.calculo.CalculoHistorico;
import com.MatheusLefa.SpringCalculadora.domain.Entity.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalculoRepository extends JpaRepository<CalculoHistorico, Long> {
    List<CalculoHistorico> findByUsuario(Usuario usuario);
}
