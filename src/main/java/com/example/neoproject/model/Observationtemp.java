package com.example.neoproject.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "observationtemp")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Observationtemp {

    @JsonProperty("id")
    @EmbeddedId
    private ObservationtempId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idsensore", nullable = false)
    @JsonBackReference
    private Sensoretemp idsensore;

    @JsonProperty("temperatura")
    @Column(name = "temperatura", nullable = false)
    private Integer temperatura;

    @MapsId("idNeonato")
    @ManyToOne(fetch = FetchType.LAZY, optional = false) //molte observation a 1 neonato
    @JoinColumn(name = "neonato_fk", nullable = false)
    @JsonBackReference
    private Neonato neonato;

    @JsonProperty("data_rilevazione")
    @Column(name = "data_rilevazione", nullable = false)
    private Instant data_rilevazione;
}