package com.MatheusLefa.SpringCalculadora.controller;

import com.MatheusLefa.SpringCalculadora.domain.Entity.user.Usuario;
import com.MatheusLefa.SpringCalculadora.dto.UsuarioRequestDTO;
import com.MatheusLefa.SpringCalculadora.dto.UsuarioResponseDTO;
import com.MatheusLefa.SpringCalculadora.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;


    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody @Valid UsuarioRequestDTO dto){
        Usuario usuario = usuarioService.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail()));
    }

    @GetMapping
    public List<UsuarioResponseDTO> listarTodos(){
        return usuarioService.listarTodos()
                .stream()
                .map(u -> new UsuarioResponseDTO(u.getId(), u.getNome(), u.getEmail()))
                .toList();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable Long id){
        Usuario u = usuarioService.buscarPorId(id);

        return new UsuarioResponseDTO(u.getId(), u.getNome(), u.getEmail());
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioRequestDTO dto){

        Usuario u = usuarioService.atualizar(id, dto);

        return new UsuarioResponseDTO(u.getId(), u.getNome(), u.getEmail());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
