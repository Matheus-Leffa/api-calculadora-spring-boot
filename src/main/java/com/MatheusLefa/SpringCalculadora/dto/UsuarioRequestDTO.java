package com.MatheusLefa.SpringCalculadora.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestDTO(

        @NotBlank(message = "Nome não pode estar vazio")
        String nome,

        @NotBlank(message = "Email não pode estar vazio")
        @Email(message = "Email inválido")
        String email

) {}
