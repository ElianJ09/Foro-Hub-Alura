package com.alura.foro_hub.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class errorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandler404(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandler400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream().
                map(errorDataValidation::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(integrityValidation.class)
    public ResponseEntity errorHandlerIntValidation(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorHandlerBusinessValidations(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    private record errorDataValidation(String selected, String error){
        public errorDataValidation(FieldError error){
            this(error.getField(),error.getDefaultMessage());
        }
    }
}
