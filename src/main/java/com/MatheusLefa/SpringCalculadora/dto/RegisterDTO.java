package com.MatheusLefa.SpringCalculadora.dto;

import com.MatheusLefa.SpringCalculadora.domain.Entity.user.UsuarioRole;

public record RegisterDTO(String nome, String email, String login, String password, UsuarioRole role) {
}
