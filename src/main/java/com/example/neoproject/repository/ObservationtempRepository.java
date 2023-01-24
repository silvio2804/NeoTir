package com.example.neoproject.repository;

import com.example.neoproject.model.Observationtemp;
import com.example.neoproject.model.ObservationtempId;
import com.example.neoproject.model.Sensoretemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface ObservationtempRepository extends JpaRepository<Observationtemp, Integer> {

    List<Observationtemp> findObservationtempByIdsensore(Integer idSensore);

    List<Observationtemp> findByIdsensore_Idpostoletto_Id(Integer id);

    @Query(value = "SELECT o FROM Observationtemp o WHERE o.idsensore= ?1 " + "AND o.data_rilevazione = (SELECT max(o.data_rilevazione) FROM Observationtemp o)")
    Observationtemp findLastObservationTemp(Sensoretemp sensoretemp);

    //List<Observationtemp> findByData_rilevazioneBetween(Instant data_rilevazioneStart, Instant data_rilevazioneEnd);

    void deleteByIdsensore(Sensoretemp idsensore);

    List<Observationtemp> findById_IdObservationTemp(Integer idObservationTemp);

    List<Observationtemp> findByNeonato_Id(Integer id);

    /*@Query(value = "SELECT Observationtemp FROM Observationtemp o WHERE o.id.dataRilevazione BETWEEN :startdate AND :endate")
    List<Observationtemp> findByFilters(@Param("startdate") String dataInizio, @Param("endate") String dataFine);*/
}