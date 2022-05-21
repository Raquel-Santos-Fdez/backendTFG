package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="estacion")
public class Estacion {

    @Id
    @GeneratedValue
    private Long id;
    private String descripcion;
    private double latitud;
    private double longitud;

    public Estacion(String descipcion, double latitud, double longitud){
        super();
        this.descripcion=descipcion;
        this.latitud=latitud;
        this.longitud=longitud;
    }

    public Estacion(){

    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitd() {
        return longitud;
    }

    public void setLongitd(double longitd) {
        this.longitud = longitd;
    }
}
