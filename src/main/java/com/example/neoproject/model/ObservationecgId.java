package com.example.neoproject.model;

import lombok.Data;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Data
@Embeddable
public class ObservationecgId implements Serializable {
    private static final long serialVersionUID = -1436930843243091930L;

    @Column(name = "data_rilevazione", nullable = false)
    private Instant dataRilevazione;
    @Column(name = "idsensore", nullable = false)
    private Integer idsensore;

    public ObservationecgId(){}

    public ObservationecgId(Instant dataRilevazione, Integer idsensore) {
        this.dataRilevazione = dataRilevazione;
        this.idsensore = idsensore;
    }
}