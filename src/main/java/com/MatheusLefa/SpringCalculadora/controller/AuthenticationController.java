package com.MatheusLefa.SpringCalculadora.controller;

import com.MatheusLefa.SpringCalculadora.domain.Entity.user.Usuario;
import com.MatheusLefa.SpringCalculadora.dto.AuthenticationDTO;
import com.MatheusLefa.SpringCalculadora.dto.LoginResponseDTO;
import com.MatheusLefa.SpringCalculadora.dto.RegisterDTO;
import com.MatheusLefa.SpringCalculadora.infra.ExceptionHandler.security.TokenService;
import com.MatheusLefa.SpringCalculadora.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.usuarioRepository.findByLogin(data.login()).isPresent()) return ResponseEntity.badRequest().build();

        String senhaCriptografada = passwordEncoder.encode(data.password());
        Usuario novoUsuario = new Usuario(data.nome(), data.email(),data.login(), senhaCriptografada, data.role());

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}
