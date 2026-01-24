package com.MatheusLefa.SpringCalculadora.repository;

import com.MatheusLefa.SpringCalculadora.domain.Entity.calculo.CalculoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculoRepository extends JpaRepository<CalculoHistorico, Long> {
}
