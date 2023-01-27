package com.example.neoproject.controller;

import com.example.neoproject.model.Observationecg;
import com.example.neoproject.model.Observationtemp;
import com.example.neoproject.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@RequestMapping("/api")
@RestController
public class ObservationController {

    @Autowired
    ObservationService observationService;

    //obs temp

    //ook
    //test 1.0
    @PostMapping("/Observationtemp/{neonatoId}")
    public ResponseEntity<Observationtemp> saveObservationtemp(@PathVariable Integer neonatoId){
        return new ResponseEntity<>(observationService.addObservationtemp(neonatoId,new Random().nextInt(1,50),neonatoId), HttpStatus.OK);
    }

    @PostMapping("/Observationtemp/saveList/{neonatoId}")
    public ResponseEntity<List<Observationtemp>> saveListObservationtemp(@PathVariable Integer neonatoId){
        return new ResponseEntity<>(observationService.addListObservationtemp(neonatoId,new Random().nextInt(1,50),neonatoId), HttpStatus.OK);
    }

    @GetMapping("/observationtemp/list/{sensorId}")
    public ResponseEntity<List<Observationtemp>> getAllObservationtemp(@PathVariable Integer sensorId){
        return new ResponseEntity<>(observationService.findAllObservationTempByIdSensore(sensorId),HttpStatus.OK);
    }
    //ok
    //test
    @GetMapping("/observationtemp/listByNeonato/{idNeonato}")
    public ResponseEntity<List<Observationtemp>> getAllObservationtempByIdNeonato(@PathVariable Integer idNeonato){
        return new ResponseEntity<>(observationService.findAllObservationTempByIdNeonato(idNeonato),HttpStatus.OK);
    }

    //test
    @GetMapping("/observationtemp/listObs/{idObservation}")
    public ResponseEntity<List<Observationtemp>> getAllObsTemp(@PathVariable Integer idObservation){
        return new ResponseEntity<>(observationService.findAllObservationTempByIdObservation(idObservation),HttpStatus.OK);
    }

    //elimina indipendentemente dal sensore
    @DeleteMapping("/observationtemp/deleteAll")
    @Transactional
    public ResponseEntity<HttpStatus> deleteAlltemps(){
        observationService.deleteAlltemps();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //elimina TUTTE le obs di quel sensore, ma non TUTTA la tabella
    @DeleteMapping("/observationtemp/delete/{idSensoretemp}")
    @Transactional
    public void deleteObservationtemp(@PathVariable Integer idSensoretemp){
        observationService.deleteObservationtempByIdSensore(idSensoretemp);
    }

    //OBS ECG

    @PostMapping("/Observationecg/{neonatoId}")
    public ResponseEntity<Observationecg> saveObservationecg(@PathVariable Integer neonatoId){
        return new ResponseEntity<>(observationService.addObservationecg(neonatoId,new Random().nextInt(1,50),neonatoId), HttpStatus.OK);
    }

    @GetMapping("/observationecg/list/{neonatoId}")
    public ResponseEntity<List<Observationecg>> getAllObservationecg(@PathVariable Integer neonatoId){
        return new ResponseEntity<>(observationService.findAllObservationEcgByIdSensore(neonatoId),HttpStatus.OK);
    }

    @DeleteMapping("/observationtecg/delete/{idSensoreecg}")
    @Transactional
    public void deleteObservationecg(@PathVariable Integer idSensoreecg){
        observationService.deleteObservationecgByIdSensore(idSensoreecg);
    }

    @DeleteMapping("/observationecg/deleteAll")
    @Transactional
    public ResponseEntity<HttpStatus> deleteAllecgs(){
        observationService.deleteAllecgs();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //filtering
}
