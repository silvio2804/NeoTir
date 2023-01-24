package com.example.neoproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "observationtemp")
public class Observationtemp {
    @EmbeddedId
    private ObservationtempId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idsensore", nullable = false)
    @JsonBackReference
    private Sensoretemp idsensore;

    @Column(name = "temperatura", nullable = false)
    private Integer temperatura;

    @MapsId("idNeonato")
    @ManyToOne(fetch = FetchType.LAZY, optional = false) //molte observation a 1 neonato
    @JoinColumn(name = "neonato_fk", nullable = false)
    @JsonBackReference
    private Neonato neonato;

    @Column(name = "data_rilevazione", nullable = false)
    private Instant data_rilevazione;
}