package com.MatheusLefa.SpringCalculadora.service;

import com.MatheusLefa.SpringCalculadora.Entity.CalculoHistorico;

import java.util.List;

public interface CalculoService<T> {
    T calcular(String operacao, double a, double b);

    List<T> listarCalculos();

    T buscarPorId(Long id);
}

