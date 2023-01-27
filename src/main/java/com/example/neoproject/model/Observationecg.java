package com.example.neoproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Data
@ToString(exclude = "idsensore")
@Entity
@Table(name = "observationecg")
public class Observationecg {
    @EmbeddedId
    private ObservationecgId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idsensore", nullable = false)
    @JsonBackReference
    private Sensoreecg idsensore;

    @Column(name = "battiti", nullable = false)
    private Integer battiti;

    @Column(name = "saturazione", nullable = false)
    private Integer saturazione;

    @MapsId("idNeonato")
    @ManyToOne(fetch = FetchType.LAZY, optional = false) //molte observation a 1 neonato
    @JoinColumn(name = "neonato_fk", nullable = false)
    @JsonBackReference
    private Neonato neonato;

    @Column(name = "data_rilevazione", nullable = false)
    private Instant data_rilevazione;
}