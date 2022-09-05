package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ruta {

    @Id
    private String ruta_id;
    private String ruta_corto;
    private String ruta_largo;

    @OneToMany(mappedBy = "ruta")
    @JsonBackReference
    private Set<Route_stop> stops=new HashSet<>();

    public Ruta(){

    }

    public Ruta(String ruta_id, String ruta_corto, String ruta_largo){
        super();
        this.ruta_id = ruta_id;
        this.ruta_corto = ruta_corto;
        this.ruta_largo = ruta_largo;
    }

    public String getRuta_id() {
        return ruta_id;
    }

    public void setRuta_id(String ruta_id) {
        this.ruta_id = ruta_id;
    }

    public String getRuta_corto() {
        return ruta_corto;
    }

    public void setRuta_corto(String ruta_corto) {
        this.ruta_corto = ruta_corto;
    }

    public String getRuta_largo() {
        return ruta_largo;
    }

    public void setRuta_largo(String ruta_largo) {
        this.ruta_largo = ruta_largo;
    }

    public Set<Route_stop> getStops() {
        return stops;
    }

    public void setStops(Set<Route_stop> stops) {
        this.stops = stops;
    }


}
