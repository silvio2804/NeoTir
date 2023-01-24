package com.example.neoproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "affettodapatologie")
public class Affettodapatologie {
    @EmbeddedId
    private AffettodapatologieId id;

    @MapsId("idneonato")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idneonato", nullable = false)
    private Neonato idneonato;

    @MapsId("nomepatologia")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nomepatologia", nullable = false)
    @JsonBackReference
    private Patologia nomepatologia;

}