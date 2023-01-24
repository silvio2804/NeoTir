package com.example.neoproject.repository;

import com.example.neoproject.model.Postoletto;
import com.example.neoproject.model.Sensoreecg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensoreEcgRepository extends JpaRepository<Sensoreecg, Integer> {

    List <Sensoreecg> findSensoreByIdpostoletto(Postoletto postoletto); //dammi tutti i sensori relativi al letto
    Sensoreecg findSensoreById(Integer id );
}