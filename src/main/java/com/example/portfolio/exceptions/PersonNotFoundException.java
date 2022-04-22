package com.example.portfolio.exceptions;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(Long id) {
        super("Persona no encontrado " + id);
    }
}
