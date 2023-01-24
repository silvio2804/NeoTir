package com.example.neoproject.exception;

public class RepartoNotFoundException extends RuntimeException{

    public RepartoNotFoundException(String reparto){
        super("il reparto "+ reparto +" non esiste");
    }
}
