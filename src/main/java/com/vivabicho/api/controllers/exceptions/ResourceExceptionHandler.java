package com.vivabicho.api.controllers.exceptions;


import com.vivabicho.api.services.exceptions.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<StandardError> argumentsNotValid(ConstraintViolationException e){
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();

        // Criar uma lista para armazenar as mensagens de erro
        List<String> errorMessages = new ArrayList<>();

        // Iterar sobre as violações de restrição e obter informações dos campos afetados
        for (ConstraintViolation<?> violation : violations) {
            String field = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            // Adicionar o campo e a mensagem de erro à lista
            errorMessages.add("Campo: " + field + " "+ errorMessage);
        }

        // Construir a resposta de erro com as mensagens coletadas
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Erro de validação");
        //err.setMessage(e.getMessage());
        err.setDetails(errorMessages);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ResponseBody
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<StandardError> sqlIntegrityConstraintViolation(SQLIntegrityConstraintViolationException e, HttpServletRequest request) {
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Email já cadastrado");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
