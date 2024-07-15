package com.alura.foro_hub.infra.errors;

public class integrityValidation extends RuntimeException {
    public integrityValidation(String s) {
        super(s);
    }
}
