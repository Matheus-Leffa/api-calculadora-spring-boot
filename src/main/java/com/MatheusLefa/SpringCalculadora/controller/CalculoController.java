package com.MatheusLefa.SpringCalculadora.controller;

import com.MatheusLefa.SpringCalculadora.domain.Entity.CalculoHistorico;
import com.MatheusLefa.SpringCalculadora.dto.CalculoRequestDTO;
import com.MatheusLefa.SpringCalculadora.dto.CalculoResponseDTO;
import com.MatheusLefa.SpringCalculadora.service.CalculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calculadora")
public class CalculoController {

    private final CalculoService calculoService;

    public CalculoController(CalculoService calculoService) {
        this.calculoService = calculoService;
    }

    @GetMapping
    public List<CalculoHistorico> listarCalculos(){
        return calculoService.listarCalculos();
    }

    @GetMapping("/{id}")
    public Object buscaPorId(@PathVariable Long id){
        return calculoService.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<CalculoResponseDTO> calcular(@RequestBody CalculoRequestDTO request){
        CalculoHistorico calculo = (CalculoHistorico) calculoService.calcular(request.operacao(), request.a(), request.b());

        CalculoResponseDTO response = new CalculoResponseDTO(
                calculo.getId(),
                calculo.getOperacao(),
                calculo.getNumero1(),
                calculo.getNumero2(),
                calculo.getResultado(),
                calculo.getDataCalculo()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

