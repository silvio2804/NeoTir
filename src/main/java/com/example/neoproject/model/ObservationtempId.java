package com.example.neoproject.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.Instant;

@Data
@Embeddable
public class ObservationtempId implements Serializable {
    private static final long serialVersionUID = 2198908211493412444L;
    @Column(name = "measure", nullable = false)
    private Integer measure;
    @Column(name = "id_observation_temp", nullable = false)
    private Integer idObservationTemp;
    @Column(name = "neonato_fk")
    private Integer idNeonato;

    public ObservationtempId (){
    }

    public ObservationtempId(Integer idMeasure, Integer idObservationTemp, Integer idNeonato) {
        this.measure = idMeasure;
        this.idObservationTemp = idObservationTemp;
        this.idNeonato = idNeonato;
    }
}