package com.MatheusLefa.SpringCalculadora.repository;

import com.MatheusLefa.SpringCalculadora.domain.Entity.CalculoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculoRepository extends JpaRepository<CalculoHistorico, Long> {
}
