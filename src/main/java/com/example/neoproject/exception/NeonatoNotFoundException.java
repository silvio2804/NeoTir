package com.example.neoproject.exception;

public class NeonatoNotFoundException extends RuntimeException{

    public NeonatoNotFoundException(Integer id){
        super("Neonato con id: " +id+ " non esiste");
    }
}
