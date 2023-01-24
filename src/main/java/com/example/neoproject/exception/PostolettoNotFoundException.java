package com.example.neoproject.exception;

public class PostolettoNotFoundException extends RuntimeException {

    public PostolettoNotFoundException(Integer id){
        super("Posto letto con id: " +id+ " non esiste");
    }
}
