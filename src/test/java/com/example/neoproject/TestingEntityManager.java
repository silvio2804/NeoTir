package com.example.neoproject;

/*
import com.example.neoproject.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.Instant;
import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
public class TestingEntityManager {

    private EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void save(){

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("Starting Transaction");

        entityManager.getTransaction().begin();
        Neonato n = (Neonato) entityManager.createQuery("select n from Neonato n where n.id=1").getSingleResult();

        Sensoretemp s = (Sensoretemp) entityManager.createQuery("select s from Sensoretemp s where s.id=1").getSingleResult();

        Observationtemp o = new Observationtemp();
        ObservationtempId obsId = new ObservationtempId(1,1,1);
        o.setId(obsId);

        o.setData_rilevazione(Instant.now());

        o.setNeonato(n);
        o.setTemperatura(new Random().nextInt(36,41));
        o.setIdsensore(s);

        entityManager.persist(o);
        entityManager.getTransaction().commit();

    }
}
*/