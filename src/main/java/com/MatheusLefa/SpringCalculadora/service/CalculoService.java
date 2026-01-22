package com.MatheusLefa.SpringCalculadora.service;

import java.util.List;

public interface CalculoService<T> {
    T calcular(String operacao, double a, double b);

    List<T> listarCalculos();

    T buscarPorId(Long id);
}

