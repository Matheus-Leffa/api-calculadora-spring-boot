package com.MatheusLefa.SpringCalculadora.domain.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class CalculoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String operacao;

    private double numero1;

    private double numero2;

    private double resultado;

    private LocalDateTime dataCalculo;

}
