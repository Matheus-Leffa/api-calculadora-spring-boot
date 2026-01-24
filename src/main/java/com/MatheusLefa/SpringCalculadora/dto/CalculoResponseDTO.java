package com.MatheusLefa.SpringCalculadora.dto;

import java.time.LocalDateTime;

public record CalculoResponseDTO(Long id,
                                 String operacao,
                                 double numero1,
                                 double numero2,
                                 double resultado,
                                 LocalDateTime dataCalculo) {
}
