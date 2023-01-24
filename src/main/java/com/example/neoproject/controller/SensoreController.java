package com.example.neoproject.controller;

import com.example.neoproject.map.dtos.sensore.SensoreEcgPostDto;
import com.example.neoproject.map.dtos.sensore.SensoreTempPostDto;
import com.example.neoproject.model.Observationecg;
import com.example.neoproject.model.Observationtemp;
import com.example.neoproject.model.Sensoreecg;
import com.example.neoproject.model.Sensoretemp;
import com.example.neoproject.service.ObservationService;
import com.example.neoproject.service.SensoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RequestMapping("/api")
@RestController
public class SensoreController {

    @Autowired
    private SensoreService sensoreService;
    @Autowired
    private ObservationService observationService;

    //history
    @GetMapping("/sensoreecg/list/{idpostoletto}")
    public ResponseEntity <List<Sensoreecg>> getAllSensorecgByIdPostoletto(@PathVariable Integer idpostoletto){
        return new ResponseEntity<>(sensoreService.findAllSensoriEcgByIdPostoLetto(idpostoletto),HttpStatus.OK);
    }

    //history
    @GetMapping("/sensoretemp/list/{idpostoletto}")
    public ResponseEntity <List<Sensoretemp>> getAllSensortempByIdPostoletto(@PathVariable Integer idpostoletto){
        return new ResponseEntity<>(sensoreService.findAllSensoriTempByIdPostoLetto(idpostoletto),HttpStatus.OK);
    }

    @GetMapping("/sensoretemp/lastvalue/{idpostoletto}")
    public ResponseEntity<Observationtemp> getLastObservationEcg(@PathVariable Integer idpostoletto){
        return new ResponseEntity<>(observationService.findLastObservationtemp(idpostoletto),HttpStatus.OK);
    }

    @GetMapping("/sensoreecg/lastvalue/{idpostoletto}")
    public ResponseEntity<Observationecg> getLastObservationTemp(@PathVariable Integer idpostoletto){
        return new ResponseEntity<>(observationService.findLastObservationecg(idpostoletto),HttpStatus.OK);
    }

    @PostMapping("/sensoreecg/add")
    public ResponseEntity <Sensoreecg> addSensoreEcg(@RequestBody SensoreEcgPostDto sensoreEcgPostDto){
        return new ResponseEntity<>(sensoreService.addSensoreEcg(sensoreEcgPostDto),HttpStatus.CREATED);
    }

    @PostMapping("/sensoretemp/add")
    public ResponseEntity <Sensoretemp> addSensoreTemp(@RequestBody SensoreTempPostDto sensoreTempPostDto){
        return new ResponseEntity<>(sensoreService.addSensoreTemp(sensoreTempPostDto),HttpStatus.CREATED);
    }

    @DeleteMapping("/sensoretemp/deleteSensoresTemp")
    @Transactional
    public ResponseEntity<HttpStatus> deleteAllSensortemp(){
        sensoreService.deleteAllSensortemp();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/sensorecg/deleteSensoresEcg")
    @Transactional
    public ResponseEntity<HttpStatus> deleteAllSensorecg(){
        sensoreService.deleteAllSensorecg();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
