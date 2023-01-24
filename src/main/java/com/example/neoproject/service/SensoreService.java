package com.example.neoproject.service;
import com.example.neoproject.repository.SensoreTempRepository;
import com.example.neoproject.exception.PostolettoNotFoundException;
import com.example.neoproject.exception.SensoreNotFoundException;
import com.example.neoproject.map.SensoreMapper;
import com.example.neoproject.map.dtos.sensore.SensoreEcgPostDto;
import com.example.neoproject.map.dtos.sensore.SensoreTempPostDto;
import com.example.neoproject.model.*;
import com.example.neoproject.repository.ObservationecgRepository;
import com.example.neoproject.repository.ObservationtempRepository;
import com.example.neoproject.repository.PostolettoRepository;
import com.example.neoproject.repository.SensoreEcgRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//qui creare le Observation in maniera randomica
@Service
public class SensoreService {

    @Autowired
    private SensoreEcgRepository sensoreEcgRepository;
    @Autowired
    private PostolettoRepository postolettoRepository;
    @Autowired
    private SensoreTempRepository sensoretempRepository;
    @Autowired
    private ObservationecgRepository obsEcgRepository;
    @Autowired
    private ObservationtempRepository obsTempRepository;

    private SensoreMapper mapper = Mappers.getMapper(SensoreMapper.class);


    //ok
    //quando creo un sensore, associo direttamente il posto letto associato
    public Sensoreecg addSensoreEcg(SensoreEcgPostDto sensoreEcgPostDto){
        Integer idpostoletto = sensoreEcgPostDto.getIdletto();
        if(!postolettoRepository.existsById(idpostoletto))
            throw new PostolettoNotFoundException(idpostoletto);
        Postoletto postoletto = postolettoRepository.findPostolettoById(idpostoletto);
        Sensoreecg s = mapper.sensoreecgPostDtoToSensoreecg(sensoreEcgPostDto);
        s.setIdpostoletto(postoletto);
        return sensoreEcgRepository.save(s);
    }

    public Sensoretemp addSensoreTemp(SensoreTempPostDto sensoreTempPostDto){
        Integer idpostoletto = sensoreTempPostDto.getIdletto();
        if(!postolettoRepository.existsById(idpostoletto))
            throw new PostolettoNotFoundException(idpostoletto);
        Postoletto postoletto = postolettoRepository.findPostolettoById(idpostoletto);
        Sensoretemp s = mapper.sensoretempPostDtoToSensoretemp(sensoreTempPostDto);
        s.setIdpostoletto(postoletto);
        return sensoretempRepository.save(s);
    }

    public Sensoreecg findSensoreEcgById(Integer id){
        if(!sensoreEcgRepository.existsById(id))
            throw new SensoreNotFoundException(id);
        return sensoreEcgRepository.findSensoreById(id);
    }

    public List<Sensoreecg> findAllSensoriEcgByIdPostoLetto (Integer idPostoletto) {
        if(!postolettoRepository.existsById(idPostoletto))
            throw new PostolettoNotFoundException(idPostoletto);
        return sensoreEcgRepository.findSensoreByIdpostoletto(postolettoRepository.findPostolettoById(idPostoletto));
    }

    public List<Sensoretemp> findAllSensoriTempByIdPostoLetto (Integer idPostoletto) {
        if(!postolettoRepository.existsById(idPostoletto))
            throw new PostolettoNotFoundException(idPostoletto);
        return sensoretempRepository.findSensoreByIdpostoletto(postolettoRepository.findPostolettoById(idPostoletto));
    }
    public void deleteAllSensortemp(){
        sensoretempRepository.deleteAll();
    }
    public void deleteAllSensorecg(){
        sensoreEcgRepository.deleteAll();
    }
}
