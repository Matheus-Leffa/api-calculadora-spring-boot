package com.MatheusLefa.SpringCalculadora.service;

import com.MatheusLefa.SpringCalculadora.exception.DivisaoPorZeroException;
import org.springframework.stereotype.Service;

@Service
public class CalculadoraServiceImpl implements CalculadoraService{

    @Override
    public double calcular(String operacao, double a, double b) {
        return switch (operacao.toLowerCase()){
            case "soma" -> a + b;
            case "subtrair" -> a - b;
            case "multiplicar" -> a * b;
            case "dividir" -> {
                if(b == 0){
                    throw new DivisaoPorZeroException();
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Operação inválida");
        };
    }
}
