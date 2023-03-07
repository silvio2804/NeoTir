package com.example.neoproject.service;

import com.example.neoproject.exception.NeonatoNotFoundException;
import com.example.neoproject.exception.PostolettoNotFoundException;
import com.example.neoproject.exception.SensoreNotFoundException;
import com.example.neoproject.map.ObservationTempMapper;
import com.example.neoproject.map.dtos.observation.ObservationPostDto;
import com.example.neoproject.model.*;
import com.example.neoproject.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.Random;



@Service
@Transactional(readOnly = true)
public class ObservationService {

    @Autowired
    private ObservationecgRepository observationecgRepository;
    @Autowired
    private ObservationtempRepository observationtempRepository;
    @Autowired
    private SensoreEcgRepository sensoreEcgRepository;
    @Autowired
    private SensoreTempRepository sensoretempRepository;
    @Autowired
    private PostolettoRepository postolettoRepository;
    @Autowired
    private NeonatoRepository neonatoRepository;

    private ObservationTempMapper mapper = Mappers.getMapper(ObservationTempMapper.class);

    private static long start = 0;
    private static long end = 0;
    private static long timeElapsed = 0;

    //Observation temp
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Observationtemp saveObservationtemp(ObservationPostDto observationPostDto, Integer neonatoId){
        if (!neonatoRepository.existsById(neonatoId))
            throw new NeonatoNotFoundException(neonatoId);
        Neonato n = neonatoRepository.findNeonatoById(neonatoId);
        Sensoretemp s = sensoretempRepository.findSensoreById(observationPostDto.getIdSensore());

        Observationtemp obs = mapper.observationtempPostDtoToObservationtemp(observationPostDto);
        obs.setNeonato(n);
        obs.setIdsensore(s);
        return observationtempRepository.save(obs);
    }


    public List <Observationtemp> findAllObservationTempByIdSensore(Integer idSensore){
        if(!sensoretempRepository.existsById(idSensore))
            throw new SensoreNotFoundException(idSensore);
        return observationtempRepository.findByIdsensore_Idpostoletto_Id(idSensore);
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List <Observationtemp> addListObservationtemp(List<ObservationPostDto> list,Integer idNeonato){
        if (!neonatoRepository.existsById(idNeonato))
            throw new NeonatoNotFoundException(idNeonato);
        Neonato n = neonatoRepository.findNeonatoById(idNeonato);
        Sensoretemp s = sensoretempRepository.findSensoreById(idNeonato);
        List <Observationtemp> observationtemps = mapper.observatiotempListPostDtoToObservationtemp(list);
        for (Observationtemp obs: observationtemps){
            obs.setNeonato(n);
            obs.setIdsensore(s);
        }
        start = System.currentTimeMillis();
        observationtempRepository.saveAll(observationtemps);
        end = System.currentTimeMillis();

        timeElapsed = end - start;
        System.out.println("neonato: "+ idNeonato + "tempo trascorso: "+ timeElapsed);
        return observationtemps;
    }

    //test
    public List <Observationtemp> findAllObservationTempByIdObservation(Integer id){
        List <Observationtemp> list = observationtempRepository.findById_IdObservationTemp(id);
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(list);
            System.out.println(json);
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return observationtempRepository.findById_IdObservationTemp(id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List <Observationtemp> findAllObservationTempByIdNeonato(Integer idNeonato){

        if(!neonatoRepository.existsById(idNeonato))
            throw new NeonatoNotFoundException(idNeonato);

        /*ObjectMapper mapper = new ObjectMapper(); stampa del json
        mapper.registerModule(new JavaTimeModule());
        try {
            String json = mapper.writeValueAsString(list);
            System.out.println(json);
        }catch (IOException exception){
            exception.printStackTrace();
        }*/
        return observationtempRepository.findByNeonato_Id(idNeonato);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Observationtemp findLastObservationtemp(Integer idPostoletto){
        if(!postolettoRepository.existsById(idPostoletto))
            throw new PostolettoNotFoundException(idPostoletto);
        Postoletto p = postolettoRepository.findPostolettoById(idPostoletto);
        Sensoretemp s = p.getSensoretemps().get(0);
        return observationtempRepository.findLastObservationTemp(s);
    }

    public Observationtemp addObservationtemp(Integer idNeonato,Integer measure, Integer idObservation){
        if(!neonatoRepository.existsById(idNeonato))
            throw new NeonatoNotFoundException(idNeonato);

        Neonato n = neonatoRepository.findNeonatoById(idNeonato);
        Sensoretemp s = sensoretempRepository.findSensoreById(idNeonato);

        Observationtemp o = new Observationtemp();
        ObservationtempId obsId = new ObservationtempId(measure,idObservation,n.getId());
        o.setId(obsId);

        o.setData_rilevazione(Instant.now());

        o.setNeonato(n);
        o.setTemperatura(new Random().nextInt(36,41));
        o.setIdsensore(s); //necessario settarlo
        return observationtempRepository.save(o);
    }

    @Transactional
    public void deleteObservationtempByIdSensore(Integer idSensoretemp){
        if(!sensoretempRepository.existsById(idSensoretemp))
            throw new SensoreNotFoundException(idSensoretemp);
        Sensoretemp s = sensoretempRepository.findSensoreById(idSensoretemp);
        observationtempRepository.deleteByIdsensore(s);
    }

    @Transactional
    public void deleteAlltemps(){
        observationtempRepository.deleteAll();
    }

    public List <Observationecg> findAllObservationEcgByIdSensore(Integer idSensore){
        if(!sensoreEcgRepository.existsById(idSensore))
            throw new SensoreNotFoundException(idSensore);
        return observationecgRepository.findByIdsensore_Id(idSensore);
    }

    public Observationtemp findObservationByNeonatoAndObservation(Integer idObs, Integer neonatoId){
        if (!neonatoRepository.existsById(neonatoId))
            throw new NeonatoNotFoundException(neonatoId);
        Neonato n = neonatoRepository.findNeonatoById(neonatoId);
        Observationtemp obs = observationtempRepository.findById_MeasureAndId_IdObservationTempAndId_IdNeonato(neonatoId,idObs,1);
        System.out.println(obs);
        return obs;
    }

    //ECG

    public Observationecg addObservationecg(Integer idNeonato, Integer measure, Integer idObservation){
        if(!neonatoRepository.existsById(idNeonato))
            throw new NeonatoNotFoundException(idNeonato);
        Neonato n = neonatoRepository.findNeonatoById(idNeonato);

        Sensoreecg s = sensoreEcgRepository.findSensoreById(idNeonato);

        Observationecg o = new Observationecg();
        ObservationecgId obsId = new ObservationecgId(idNeonato,idObservation,measure);

        o.setId(obsId);
        o.setBattiti(new Random().nextInt(100,191));
        o.setSaturazione(new Random().nextInt(80,101));
        o.setData_rilevazione(Instant.now());
        o.setIdsensore(s); //necessario settarlo
        o.setNeonato(n);
        return observationecgRepository.save(o);
    }

    public Observationecg findLastObservationecg(Integer idPostoletto){
        if(!postolettoRepository.existsById(idPostoletto))
            throw new PostolettoNotFoundException(idPostoletto);
        Postoletto p = postolettoRepository.findPostolettoById(idPostoletto);
        Sensoreecg s = p.getSensoreecgs().get(0);
        return observationecgRepository.findLastObservationecg(s);
    }

    @Transactional
    public void deleteObservationecgByIdSensore(Integer observationecg){
        observationecgRepository.deleteById(observationecg);
    }

    @Transactional
    public void deleteAllecgs(){
        observationecgRepository.deleteAll();
    }
}


