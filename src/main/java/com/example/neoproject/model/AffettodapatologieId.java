package com.example.neoproject.model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AffettodapatologieId implements Serializable {
    private static final long serialVersionUID = -3323429408190199729L;
    @Column(name = "idneonato", nullable = false)
    private Integer idneonato;
    @Lob
    @Column(name = "nomepatologia", nullable = false)
    private String nomepatologia;

    public String getNomepatologia() {
        return nomepatologia;
    }

    public void setNomepatologia(String nomepatologia) {
        this.nomepatologia = nomepatologia;
    }

    public Integer getIdneonato() {
        return idneonato;
    }

    public void setIdneonato(Integer idneonato) {
        this.idneonato = idneonato;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomepatologia, idneonato);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AffettodapatologieId entity = (AffettodapatologieId) o;
        return Objects.equals(this.nomepatologia, entity.nomepatologia) &&
                Objects.equals(this.idneonato, entity.idneonato);
    }
}