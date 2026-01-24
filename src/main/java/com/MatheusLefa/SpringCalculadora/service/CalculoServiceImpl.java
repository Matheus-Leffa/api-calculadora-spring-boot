package com.MatheusLefa.SpringCalculadora.service;

import com.MatheusLefa.SpringCalculadora.domain.Entity.calculo.CalculoHistorico;
import com.MatheusLefa.SpringCalculadora.domain.Entity.usuario.Usuario;
import com.MatheusLefa.SpringCalculadora.dto.CalculoResponseDTO;
import com.MatheusLefa.SpringCalculadora.exception.DivisaoPorZeroException;
import com.MatheusLefa.SpringCalculadora.repository.CalculoRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalculoServiceImpl implements CalculoService {

    private final CalculoRepository calculoRepository;

    public CalculoServiceImpl(CalculoRepository calculoRepository) {
        this.calculoRepository = calculoRepository;
    }


    @Override
    public CalculoHistorico calcular(String operacao, double a, double b) {

        Usuario usuarioLogado = (Usuario) SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getPrincipal();

        CalculoHistorico calculo = new CalculoHistorico();
        calculo.setOperacao(operacao);
        calculo.setNumero1(a);
        calculo.setNumero2(b);
        calculo.setDataCalculo(LocalDateTime.now());
        calculo.setUsuario(usuarioLogado);

        double resultado =  switch (operacao.toLowerCase()) {
            case "soma" -> a + b;
            case "subtrair" -> a - b;
            case "multiplicar" -> a * b;
            case "dividir" -> {
                if (b == 0) {
                    throw new DivisaoPorZeroException("Não é possível dividir por 0");
                }
                yield a/b;
            }
            default -> throw new IllegalArgumentException("Operação inválida");
        };

        calculo.setResultado(resultado);
        return calculoRepository.save(calculo);
    }

    @Override
    public List<CalculoResponseDTO> listarCalculos() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        boolean isAdmin = usuarioLogado.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        List<CalculoHistorico> calculos;

        if(isAdmin){
            calculos = calculoRepository.findAll();
        }else {
            calculos = calculoRepository.findByUsuario(usuarioLogado);
        }

        return calculos
                .stream()
                .map(calculo -> new CalculoResponseDTO(
                        calculo.getId(),
                        calculo.getOperacao(),
                        calculo.getNumero1(),
                        calculo.getNumero2(),
                        calculo.getResultado(),
                        calculo.getDataCalculo()
                ))
                .toList();
    }

    @Override
    public CalculoHistorico buscarPorId(Long id) {
        return calculoRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Cálculo não encontrado"));
    }


}
