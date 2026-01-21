package com.MatheusLefa.SpringCalculadora.service;

import com.MatheusLefa.SpringCalculadora.Entity.Usuario;
import com.MatheusLefa.SpringCalculadora.dto.UsuarioResponseDTO;
import com.MatheusLefa.SpringCalculadora.exception.EmailJaCadastradoException;
import com.MatheusLefa.SpringCalculadora.exception.RecursoNaoEncontradoException;
import com.MatheusLefa.SpringCalculadora.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario criar(UsuarioResponseDTO dto){
        if(usuarioRepository.existsByEmail(dto.email())){
            throw new EmailJaCadastradoException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());

        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));
    }

    public Usuario atualizar(Long id,UsuarioResponseDTO dto){
        Usuario usuario = buscarPorId(id);

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());

        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id){
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }
}
