package com.example.neoproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "patologia")
public class Patologia {
    @Id
    @Column(name = "nome", nullable = false)
    private String id;


    @Column(name = "categoria")
    private String categoria;

    @ManyToMany
    @JoinTable(name = "affettodapatologie",
            joinColumns = @JoinColumn(name = "nomepatologia"),
            inverseJoinColumns = @JoinColumn(name = "idneonato"))
    private List<Neonato> neonatoes = new ArrayList<>();

    public List<Neonato> getNeonatoes() {
        return neonatoes;
    }

    public void setNeonatoes(List<Neonato> neonatoes) {
        this.neonatoes = neonatoes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}