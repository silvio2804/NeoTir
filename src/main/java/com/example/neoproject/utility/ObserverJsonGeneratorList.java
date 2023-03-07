package com.example.neoproject.utility;

import com.example.neoproject.map.dtos.observation.ObservationPostDto;
import com.example.neoproject.model.ObservationtempId;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ObserverJsonGeneratorList {

    private static Integer NUM_BAMBINI = 10;
    private static Integer OBSERVATIONS = 20000;
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String argv[]){

        mapper.registerModule(new JavaTimeModule());

        List<ObservationPostDto> list = new ArrayList<>();

        for (int i = 5; i <= 5; i++) {
            for (int j = 1; j <= OBSERVATIONS; j++) {
                ObservationPostDto observationPostDto = new ObservationPostDto();
                ObservationtempId id = new ObservationtempId();
                id.setIdNeonato(i);
                id.setMeasure(j);
                id.setIdObservationTemp(i);
                observationPostDto.setId(id);
                observationPostDto.setTemperatura(new Random().nextInt(37,41));
                observationPostDto.setIdSensore(i);
                observationPostDto.setData_rilevazione(Instant.now());
                list.add(observationPostDto);
            }
            writeIntoFile(list,i);
        }
    }

    public static void writeIntoFile(List<ObservationPostDto> observationPostDtoList,int i){
        try {
            System.out.println("file numero: "+i);
            String val = mapper.writeValueAsString(observationPostDtoList);
            FileWriter file = new FileWriter("/home/venturino/Scrivania/obs" + i+".json");
            file.write(val);
            file.close();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            observationPostDtoList.clear();
        }
    }
}
