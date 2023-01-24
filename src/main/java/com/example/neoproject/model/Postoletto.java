package com.example.neoproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "postoletto")
public class Postoletto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomereparto")
    @JsonBackReference
    private Reparto nomereparto;

    @OneToMany(mappedBy = "idpostoletto",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Neonato> neonatoes = new ArrayList<>();

    @OneToMany(mappedBy = "idpostoletto",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Sensoretemp> sensoretemps = new ArrayList<>();

    @OneToMany(mappedBy = "idpostoletto")
    @JsonManagedReference
    private List<Sensoreecg> sensoreecgs = new ArrayList<>();

    @OneToMany(mappedBy = "idpostoletto",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Dashboard> dashboards = new ArrayList<>();
}