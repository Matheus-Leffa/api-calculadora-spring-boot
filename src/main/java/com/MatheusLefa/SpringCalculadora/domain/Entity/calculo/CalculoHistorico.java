package com.MatheusLefa.SpringCalculadora.domain.Entity.calculo;

import com.MatheusLefa.SpringCalculadora.domain.Entity.usuario.Usuario;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
