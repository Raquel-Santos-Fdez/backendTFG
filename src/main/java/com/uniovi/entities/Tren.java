package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tren {

    @Id
    @GeneratedValue
    private Long id;


    @OneToMany(mappedBy = "tren", cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<Incidencia> incidencias=new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
