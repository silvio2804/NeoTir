package com.example.neoproject.service;
import com.example.neoproject.exception.NeonatoNotFoundException;
import com.example.neoproject.exception.PostolettoNotFoundException;
import com.example.neoproject.exception.SensoreNotFoundException;
import com.example.neoproject.model.*;
import com.example.neoproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



@Service
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

    //OBS TEMP

    public List <Observationtemp> findAllObservationTempByIdSensore(Integer idSensore){
        if(!sensoretempRepository.existsById(idSensore))
            throw new SensoreNotFoundException(idSensore);
        return observationtempRepository.findByIdsensore_Idpostoletto_Id(idSensore);
    }

    public List <Observationtemp> addListObservationtemp(Integer idNeonato, Integer measure, Integer idObservation){

        List <Observationtemp> list = new ArrayList<>();
        long start = 0;
        long end = 0;
        long timeElapsed = 0;

        for (int j = 1; j <= 10; j++){

            Neonato n = neonatoRepository.findNeonatoById(j);
            Sensoretemp s = sensoretempRepository.findSensoreById(j);

            for (int i = 1; i <= 20000; i++){
                Observationtemp o = new Observationtemp();
                ObservationtempId obsId = new ObservationtempId(i,j,j);
                o.setId(obsId);

                o.setData_rilevazione(Instant.now());

                o.setNeonato(n);
                o.setTemperatura(new Random().nextInt(36,41));
                o.setIdsensore(s); //necessario settarlo
                list.add(o);
            }
            start = System.currentTimeMillis();
            observationtempRepository.saveAll(list);
            end = System.currentTimeMillis();
            timeElapsed = end - start;
            System.out.println("neonato: "+ j + "tempo trascorso: "+ timeElapsed);
            list.clear();
        }
        return null;
    }

    //test
    public List <Observationtemp> findAllObservationTempByIdObservation(Integer id){
        return observationtempRepository.findById_IdObservationTemp(id);
    }

    public List <Observationtemp> findAllObservationTempByIdNeonato(Integer id){
        return observationtempRepository.findByNeonato_Id(id);
    }


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

    public void deleteObservationtempByIdSensore(Integer idSensoretemp){
        if(!sensoretempRepository.existsById(idSensoretemp))
            throw new SensoreNotFoundException(idSensoretemp);
        Sensoretemp s = sensoretempRepository.findSensoreById(idSensoretemp);
        observationtempRepository.deleteByIdsensore(s);
    }
    public void deleteAlltemps(){
        observationtempRepository.deleteAll();
    }

    public List <Observationecg> findAllObservationEcgByIdSensore(Integer idSensore){
        if(!sensoreEcgRepository.existsById(idSensore))
            throw new SensoreNotFoundException(idSensore);
        return observationecgRepository.findByIdsensore_Id(idSensore);
    }

    //ECG

    public Observationecg addObservationecg(Integer idNeonato, Integer measure, Integer idObservation){
        if(!neonatoRepository.existsById(idNeonato))
            throw new NeonatoNotFoundException(idNeonato);
        Neonato n = neonatoRepository.findNeonatoById(idNeonato);

        Sensoreecg s = sensoreEcgRepository.findSensoreById(idNeonato);

        Observationecg o = new Observationecg();
        ObservationecgId obsId = new ObservationecgId(idNeonato,idObservation,measure);
        ;
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

    public void deleteObservationecgByIdSensore(Integer observationecg){
        observationecgRepository.deleteById(observationecg);
    }

    public void deleteAllecgs(){
        observationecgRepository.deleteAll();
    }
}


