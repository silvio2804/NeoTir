package com.example.neoproject.exception;

public class DashboardException extends RuntimeException{

    public DashboardException(Integer id){
        super("Dasbhoard con id: "+ id+ " non trovata");
    }
}
