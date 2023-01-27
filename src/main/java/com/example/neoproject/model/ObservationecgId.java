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

    @Column(name = "measure", nullable = false)
    private Integer measure;
    @Column(name = "id_observation_ecg", nullable = false)
    private Integer idObservationEcg;
    @Column(name = "neonato_fk")
    private Integer idNeonato;

    public ObservationecgId(){}

    public ObservationecgId(Integer idNeonato, Integer idObservationecg,  Integer idMeasure) {
        this.measure = idMeasure;
        this.idObservationEcg = idObservationecg;
        this.idNeonato = idNeonato;
    }
}