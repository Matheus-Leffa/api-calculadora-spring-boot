package com.MatheusLefa.SpringCalculadora.infra;

import com.MatheusLefa.SpringCalculadora.exception.DivisaoPorZeroException;
import com.MatheusLefa.SpringCalculadora.exception.EmailJaCadastradoException;
import com.MatheusLefa.SpringCalculadora.exception.RecursoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DivisaoPorZeroException.class)
    public ResponseEntity<String> handleDivisaoInvalida(DivisaoPorZeroException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    public ResponseEntity<String> handleRecursoNaoEncontrado(RecursoNaoEncontradoException ex){
        return ResponseEntity
                .status((HttpStatus.NOT_FOUND))
                .body(ex.getMessage());
    }

    public ResponseEntity<String> handleEmailJaCadastrado(EmailJaCadastradoException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidacao(MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        erros.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }
}

