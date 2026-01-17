package com.MatheusLefa.SpringCalculadora.controller;

import com.MatheusLefa.SpringCalculadora.service.CalculoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calculadora")
public class CalculoController {

    private final CalculoService calculoService;

    public CalculoController(CalculoService calculoService) {
        this.calculoService = calculoService;
    }

    @GetMapping("/{operacao}")
    public Object calcular(@PathVariable String operacao, @RequestParam double a, @RequestParam double b){
        return calculoService.calcular(operacao, a, b);
    }
}

