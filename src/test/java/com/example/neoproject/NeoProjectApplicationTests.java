package com.example.neoproject;

import com.example.neoproject.model.*;
import com.example.neoproject.repository.*;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(properties = {"spring.jpa.properties.hibernate.generate_statistics=true",
        "logging.level.org.hibernate.stat=debug"})
 public class NeoProjectApplicationTests {

    @Autowired
    private PostolettoRepository postolettoRepository;

    @Autowired
    private NeonatoRepository neonatoRepository;

    @Autowired
    private RepartoRepository repartoRepository;

    @Autowired
    private SensoreTempRepository sensoreTempRepository;

    @Autowired
    private SensoreEcgRepository sensoreEcgRepository;
    @Autowired
    private ObservationtempRepository observationtempRepository;

    private static int START_NEONATI= 1;
    private static int END_NEONATI = 1;

    private static int START_SENSORI = 1;
    private static int END_SENSORI = 10000;

    private static int BEGIN_OBSERVATION = 1;
    private static int END_OBSERVATION = 10000;
    @Autowired
    private ObservationecgRepository observationecgRepository;

    private long startTime = 0, endTime = 0, timeElapsed = 0;


    @Test
        public void createPostiletto() throws InterruptedException {

            Reparto r = new Reparto();
            r.setId("neo reparto");
            r.setOspedale("san leonardo");
            r.setLivello(1);
            repartoRepository.save(r);
            Postoletto p = new Postoletto();
            p.setNomereparto(r);
            Postoletto p1 = new Postoletto();
            p1.setNomereparto(r);
            Thread.sleep(3000);
            postolettoRepository.saveAll(List.of(p,p1));
        }

        @Test
        public void createNeonato(){
            EasyRandomParameters parameters = new EasyRandomParameters();
            parameters.excludeField(FieldPredicates.named("id").and(FieldPredicates.inClass(Neonato.class)));
            parameters.excludeField(FieldPredicates.named("idpostoletto").and(FieldPredicates.inClass(Neonato.class)));
            EasyRandom gen = new EasyRandom(parameters);
            startTime = System.currentTimeMillis();
            for (int i = START_NEONATI; i <=END_NEONATI; i++){

                Neonato n = gen.nextObject(Neonato.class);
                Postoletto p = postolettoRepository.findPostolettoById(i);
                Reparto r = repartoRepository.findRepartoById("neo reparto");
                p.setNomereparto(r);
                n.setIdpostoletto(p);
                neonatoRepository.save(n);
            }
            endTime = System.currentTimeMillis();
            timeElapsed = endTime - startTime;
            System.out.println(timeElapsed);
        }

        @Test
        public void createSensoreTemp(){

            EasyRandomParameters parameters = new EasyRandomParameters();
            parameters.excludeField(FieldPredicates.named("id").and(FieldPredicates.inClass(Sensoretemp.class)));
            parameters.excludeField(FieldPredicates.named("idpostoletto").and(FieldPredicates.inClass(Sensoretemp.class)));
            EasyRandom gen = new EasyRandom(parameters);
            startTime = System.currentTimeMillis();
            for (int i = START_SENSORI; i <= END_SENSORI; i++){
                Sensoretemp s = gen.nextObject(Sensoretemp.class);
                Postoletto p = postolettoRepository.findPostolettoById(i);
                s.setIdpostoletto(p);
                sensoreTempRepository.save(s);
            }
    }

    @Test
    public void createSensoreEcg(){
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.excludeField(FieldPredicates.named("id").and(FieldPredicates.inClass(Sensoreecg.class)));
        parameters.excludeField(FieldPredicates.named("idpostoletto").and(FieldPredicates.inClass(Sensoreecg.class)));
        EasyRandom gen = new EasyRandom(parameters);
        startTime = System.currentTimeMillis();
        for (int i = START_SENSORI; i <= END_SENSORI; i++){
            Sensoreecg s = gen.nextObject(Sensoreecg.class);
            Postoletto p = postolettoRepository.findPostolettoById(i);
            s.setIdpostoletto(p);
            sensoreEcgRepository.save(s);
        }
        endTime = System.currentTimeMillis();
        timeElapsed = endTime - startTime;
        System.out.println("------------tempo trascorso:" +timeElapsed+" --------------");
    }

    //ok
   @Test
    public void deleteAll(){
            neonatoRepository.deleteAll();
    }

    @Test
    public void createAllObsTemp(){
        List<Observationtemp> list = new ArrayList<>();
        for (int i = START_NEONATI; i <= END_NEONATI; i++){
            startTime = System.currentTimeMillis();
            Sensoretemp s = sensoreTempRepository.findSensoreById(i);
            Neonato n = neonatoRepository.findNeonatoById(i);
            System.out.println("-----------------------neonato numero: "+ i + "----------------------------");

            for (int j = BEGIN_OBSERVATION; j <= END_OBSERVATION; j++){
                System.out.println();
                Observationtemp obs = new Observationtemp();
                ObservationtempId obsId = new ObservationtempId(Instant.now(),s.getId());
                obs.setId(obsId);
                obs.setTemperatura(new Random().nextInt(36,41));
                obs.setNeonato(n);
                obs.setIdsensore(s);
                obs.setIdObservationtemp(i);
                list.add(obs);
                System.out.println();
            }
            observationtempRepository.saveAll(list);
            endTime = System.currentTimeMillis();
            timeElapsed = endTime - startTime;
            System.out.println("------------tempo trascorso per 1 bambino: " +timeElapsed+" --------------");
            list.clear();
        }
    }

    @Test
    public void createAllObsTempNoRandom(){

        for (int i = START_NEONATI; i <= END_NEONATI; i++){
            startTime = System.currentTimeMillis();
            Observationtemp obs = new Observationtemp();
            Sensoretemp s = sensoreTempRepository.findSensoreById(i);
            Neonato n = neonatoRepository.findNeonatoById(i);
            System.out.println("-----------------------neonato numero: "+ i + "----------------------------");
            for (int j = BEGIN_OBSERVATION; j <= END_OBSERVATION; j++){
                System.out.println();
                ObservationtempId obsId = new ObservationtempId(Instant.now(),s.getId());
                obs.setId(obsId);
                obs.setTemperatura(36);
                obs.setNeonato(n);
                obs.setIdsensore(s);
                obs.setIdObservationtemp(i);
                observationtempRepository.save(obs);
                System.out.println();
            }
            endTime = System.currentTimeMillis();
            timeElapsed = endTime - startTime;
            System.out.println("------------tempo trascorso per 1 bambino: " +timeElapsed+" --------------");
        }
    }

    @Test
    public void createBatch(){
            startTime = System.currentTimeMillis();
            Sensoretemp s = sensoreTempRepository.findSensoreById(1);
            Neonato n = neonatoRepository.findNeonatoById(1);
            Observationtemp obs = new Observationtemp();
            ObservationtempId obsId = new ObservationtempId(Instant.now(), s.getId());
            obs.setId(obsId);
            obs.setTemperatura(36);
            obs.setNeonato(n);
            obs.setIdsensore(s);
            obs.setIdObservationtemp(1);
            observationtempRepository.saveAll(List.of(obs));
    }


    @Test
    public void createObs(){
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.excludeField(FieldPredicates.named("idsensore").and(FieldPredicates.inClass(Observationtemp.class)));
        parameters.excludeField(FieldPredicates.named("id_observation_temp").and(FieldPredicates.inClass(Observationtemp.class)));
        parameters.excludeField(FieldPredicates.named("neonato_fk").and(FieldPredicates.inClass(Observationtemp.class)));
        parameters.excludeField(FieldPredicates.named("id").and(FieldPredicates.inClass(Observationtemp.class)));
        parameters.excludeField(FieldPredicates.named("neonato").and(FieldPredicates.inClass(Observationtemp.class)));
        parameters.excludeField(FieldPredicates.named("idObservationtemp").and(FieldPredicates.inClass(Observationtemp.class)));
        EasyRandom gen = new EasyRandom(parameters);

        Observationtemp obs = gen.nextObject(Observationtemp.class);
        Sensoretemp s = sensoreTempRepository.findSensoreById(1);

        ObservationtempId obsId = new ObservationtempId(Instant.now(),s.getId());
        obs.setId(obsId);

        obs.setTemperatura(new Random().nextInt(36,41));

        Neonato n = neonatoRepository.findNeonatoById(1);

        obs.setNeonato(n);
        obs.setIdsensore(s);

        obs.setIdObservationtemp(1);
        observationtempRepository.save(obs);
    }


}
