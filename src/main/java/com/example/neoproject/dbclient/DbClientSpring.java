/*package com.example.neoproject.dbclient;

import com.example.neoproject.model.Neonato;
import com.example.neoproject.model.Observationtemp;
import com.example.neoproject.model.ObservationtempId;
import com.example.neoproject.model.Sensoretemp;
import com.example.neoproject.repository.NeonatoRepository;
import com.example.neoproject.repository.ObservationtempRepository;
import com.example.neoproject.repository.SensoreTempRepository;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Component
public class DbClientSpring implements CommandLineRunner {

    @Autowired
    private static NeonatoRepository neonatoRepository;
    @Autowired
    private static SensoreTempRepository sensoreTempRepository;
    @Autowired
    private static ObservationtempRepository observationtempRepository;

    private static int START_NEONATI= 2;
    private static int END_NEONATI = 2;

    private static int BEGIN_OBSERVATION = 1;
    private static int END_OBSERVATION = 10000;

    private static long startTime = 0, endTime = 0, timeElapsed = 0;

    public static void main(String args[]){
        SpringApplication.run(DbClientSpring.class, args);
        }

    @Override
    public void run(String... args) throws Exception {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.excludeField(FieldPredicates.named("idsensore").and(FieldPredicates.inClass(Observationtemp.class)));
        parameters.excludeField(FieldPredicates.named("id_observation_temp").and(FieldPredicates.inClass(Observationtemp.class)));
        parameters.excludeField(FieldPredicates.named("neonato_fk").and(FieldPredicates.inClass(Observationtemp.class)));
        parameters.excludeField(FieldPredicates.named("id").and(FieldPredicates.inClass(Observationtemp.class)));
        parameters.excludeField(FieldPredicates.named("neonato").and(FieldPredicates.inClass(Observationtemp.class)));
        //parameters.excludeField(FieldPredicates.named("idObservationtemp").and(FieldPredicates.inClass(Observationtemp.class)));
        EasyRandom gen = new EasyRandom(parameters);

        for (int i = START_NEONATI; i <= END_NEONATI; i++) {
            startTime = System.currentTimeMillis();
            Observationtemp obs = gen.nextObject(Observationtemp.class);
            Sensoretemp s = sensoreTempRepository.findSensoreById(i);
            Neonato n = neonatoRepository.findNeonatoById(i);
            System.out.println("-----------------------neonato numero: " + i + "----------------------------");
            for (int j = BEGIN_OBSERVATION; j <= END_OBSERVATION; j++) {
                ObservationtempId obsId = new ObservationtempId(Instant.now(), s.getId());
                obs.setId(obsId);
                obs.setTemperatura(new Random().nextInt(36, 41));
                obs.setNeonato(n);
                obs.setIdsensore(s);
                observationtempRepository.save(obs);
            }
            endTime = System.currentTimeMillis();
            timeElapsed = endTime - startTime;
            System.out.println("------------tempo trascorso per 1 bambino: " + timeElapsed + " --------------");
        }
    }
}

*/
