package com.example.neoproject.repository;

import com.example.neoproject.model.Postoletto;
import com.example.neoproject.model.Sensoretemp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SensoreTempRepository extends JpaRepository<Sensoretemp, Integer> {

    List<Sensoretemp> findSensoreByIdpostoletto(Postoletto postoletto); //dammi tutti i sensori relativi al letto

    Sensoretemp findSensoreById(Integer id );
}