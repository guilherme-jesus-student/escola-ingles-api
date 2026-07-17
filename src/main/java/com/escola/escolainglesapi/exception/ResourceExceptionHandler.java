package com.escola.escolainglesapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.Instant;

@RestControllerAdvice
public class ResourceExceptionHandler {

    // 1. Captura a RuntimeException lançada pelo .orElseThrow() do seu Service (Ex: ID não encontrado)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND; // Código 404

        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Recurso não encontrado");
        err.setMessage(e.getMessage()); // Captura exatamente o seu "Professor não encontrado com ID: X"
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    // 2. Captura erros quando o Spring não encontra a rota digitada (Ex: erro de digitação na URL)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<StandardError> noHandlerFound(NoHandlerFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND; // Código 404

        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Rota não encontrada");
        err.setMessage("O caminho digitado não existe na API. Verifique a URL.");
        err.setPath(request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}