package com.MatheusLefa.SpringCalculadora.dto;

import com.MatheusLefa.SpringCalculadora.domain.Entity.user.UsuarioRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(@NotBlank String nome,
                          @Email String email,
                          @NotBlank String login,
                          @NotBlank String password,
                          UsuarioRole role) {
}
