package com.MatheusLefa.SpringCalculadora.controller;

import com.MatheusLefa.SpringCalculadora.service.CalculadoraService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculadora")
public class CalculadoraController {

    private final CalculadoraService calculadoraService;

    public CalculadoraController(CalculadoraService calculadoraService) {
        this.calculadoraService = calculadoraService;
    }

    @GetMapping("/{operacao}")
    public double calcular(@PathVariable String operacao, @RequestParam double a, @RequestParam double b){
        return calculadoraService.calcular(operacao, a, b);
    }
}

