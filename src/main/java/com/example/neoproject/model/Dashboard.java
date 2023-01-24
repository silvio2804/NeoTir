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
@ToString(exclude = {"idpostoletto","widgets"})
@Table(name = "dashboard")
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idpostoletto", nullable = false)
    @JsonBackReference
    private Postoletto idpostoletto;

    @OneToMany(mappedBy = "iddashboard", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Widget> widgets = new ArrayList<>();
}