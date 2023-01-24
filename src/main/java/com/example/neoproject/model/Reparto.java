package com.example.neoproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "reparto")
public class Reparto {
    @Id
    @Column(name = "nome", nullable = false)
    private String id;

    @Column(name = "ospedale", nullable = false)
    private String ospedale;

    @Column(name = "livello", nullable = false)
    private Integer livello;

    @OneToMany(mappedBy = "nomereparto",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Postoletto> postolettos = new ArrayList<>();
}