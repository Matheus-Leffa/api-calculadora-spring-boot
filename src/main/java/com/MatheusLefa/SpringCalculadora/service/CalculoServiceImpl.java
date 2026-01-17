package com.MatheusLefa.SpringCalculadora.service;

import com.MatheusLefa.SpringCalculadora.Entity.CalculoHistorico;
import com.MatheusLefa.SpringCalculadora.exception.DivisaoPorZeroException;
import com.MatheusLefa.SpringCalculadora.repository.CalculoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CalculoServiceImpl implements CalculoService {

    private final CalculoRepository calculoRepository;

    public CalculoServiceImpl(CalculoRepository calculoRepository) {
        this.calculoRepository = calculoRepository;
    }


    @Override
    public Object calcular(String operacao, double a, double b) {

        CalculoHistorico calculo = new CalculoHistorico();
        calculo.setOperacao(operacao);
        calculo.setNumero1(a);
        calculo.setNumero2(b);
        calculo.setDataCalculo(LocalDateTime.now());

        double resultado =  switch (operacao.toLowerCase()) {
            case "soma" -> a + b;
            case "subtrair" -> a - b;
            case "multiplicar" -> a * b;
            case "dividir" -> {
                if (b == 0) {
                    throw new DivisaoPorZeroException();
                }
                yield a/b;
            }
            default -> throw new IllegalArgumentException("Operação inválida");
        };

        calculo.setResultado(resultado);
        calculoRepository.save(calculo);

        return resultado;
    }

    @Override
    public List<CalculoHistorico> listarCalculos() {
        return calculoRepository.findAll();
    }

    @Override
    public CalculoHistorico buscarPorId(Long id) {
        return calculoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cálculo não encontrado"));
    }


}
