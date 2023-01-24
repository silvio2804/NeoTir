package com.example.neoproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "widget")
public class Widget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dimensione")
    private Double dimensione;

    @Column(name = "posizione")
    private Double posizione;

    @Column(name = "testo")
    private String testo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "iddashboard", nullable = false)
    @JsonBackReference
    private Dashboard iddashboard;

}