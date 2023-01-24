package com.example.neoproject.exception;

public class SensoreNotFoundException extends RuntimeException {
    public SensoreNotFoundException(Integer id) {
        super("Sensore con id: " +id+ "non esiste");
    }
}
