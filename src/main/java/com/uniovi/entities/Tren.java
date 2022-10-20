package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tren {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "tren", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Incidencia> incidencias=new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(Set<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }
}
