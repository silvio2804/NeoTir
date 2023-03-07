package com.example.neoproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "iddashboard",cascade = CascadeType.ALL,orphanRemoval = true) //orphanRemoval -> rimuove le entità quando faccio clear sulla lista di widget della dashboard (in update),
                                                                                        // altrimenti il clear non funziona e fa append
    @JsonManagedReference
    private List<Widget> widgets = new ArrayList<>();

    public void addChild(Widget widget)
    {
        this.widgets.add(widget);

    }

    public void removeChild(Widget widget)
    {
        this.widgets.remove(widget);
    }

}