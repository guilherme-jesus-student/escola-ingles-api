package com.escola.escolainglesapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Captura qualquer erro genérico na API e devolve uma mensagem limpa
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> tratarErroGenerico(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Erro ao processar requisição: " + e.getMessage());
    }
}
// GlobalExceptionHandler: Centraliza o tratamento de exceções da API para evitar retornos brutos de erro e manter as respostas amigáveis.