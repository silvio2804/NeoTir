package com.example.neoproject.repository;

import com.example.neoproject.model.Observationtemp;
import com.example.neoproject.model.ObservationtempId;
import com.example.neoproject.model.Sensoretemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ObservationtempRepository extends JpaRepository<Observationtemp, Integer> {

    List<Observationtemp> findObservationtempByIdsensore(Integer idSensore);

    List<Observationtemp> findByIdsensore_Idpostoletto_Id(Integer id);

    @Query(value = "SELECT o FROM Observationtemp o WHERE o.idsensore= ?1 " + "AND o.data_rilevazione = (SELECT max(o.data_rilevazione) FROM Observationtemp o)")
    Observationtemp findLastObservationTemp(Sensoretemp sensoretemp);

    void deleteByIdsensore(Sensoretemp idsensore);

    List<Observationtemp> findById_IdObservationTemp(Integer idObservationTemp);

    List<Observationtemp> findByNeonato_Id(Integer id);

    Observationtemp findById_MeasureAndId_IdObservationTempAndId_IdNeonato(Integer idNeonato, Integer idObservationTemp, Integer measure);

    @Override
    <S extends Observationtemp> List<S> saveAll(Iterable<S> entities);
}