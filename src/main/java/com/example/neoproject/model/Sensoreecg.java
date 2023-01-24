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
@ToString(exclude = {"idpostoletto"})
@Entity
@Table(name = "sensoreecg")
public class Sensoreecg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "modello")
    private String modello;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idpostoletto", nullable = false)
    @JsonBackReference
    private Postoletto idpostoletto;

    @OneToMany(mappedBy = "idsensore",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Observationecg> observationecgs = new ArrayList<>();
}