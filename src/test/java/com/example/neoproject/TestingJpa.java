package com.example.neoproject;

import com.example.neoproject.model.Neonato;
import com.example.neoproject.model.Observationtemp;
import com.example.neoproject.model.ObservationtempId;
import com.example.neoproject.model.Sensoretemp;
import com.example.neoproject.repository.NeonatoRepository;
import com.example.neoproject.repository.ObservationtempRepository;
import com.example.neoproject.repository.SensoreTempRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class TestingJpa {

    @Autowired
    private ObservationtempRepository observationtempRepository;
    @Autowired
    private NeonatoRepository neonatoRepository;
    @Autowired
    private SensoreTempRepository sensoreTempRepository;

    @Test
    @Rollback(value = false)
    public void saveObs(){
        List<Observationtemp> list = new ArrayList<>();
        long start = 0;
        long end = 0;
        long timeElapsed = 0;

        for (int j = 1; j <= 10; j++){

            Neonato n = neonatoRepository.findNeonatoById(j);
            Sensoretemp s = sensoreTempRepository.findSensoreById(j);

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
            System.out.println("neonato: "+ j + " tempo trascorso: "+ timeElapsed);
            list.clear();
        }
    }


}
