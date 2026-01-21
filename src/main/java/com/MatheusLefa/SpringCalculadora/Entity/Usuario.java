package com.MatheusLefa.SpringCalculadora.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private int idade;

    @Column(nullable = false, unique = true)
    private String email;

}
