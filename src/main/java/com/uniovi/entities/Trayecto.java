package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Trayecto {

    @Id
    private String id;

    @ManyToOne
    private Ruta ruta;

    public Trayecto(String id, Ruta ruta) {
        super();
        this.id = id;
        this.ruta = ruta;
    }

    public Trayecto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String trayecto_id) {
        this.id = trayecto_id;
    }

    public Ruta getRoute() {
        return ruta;
    }

    public void setRoute(Ruta ruta) {
        this.ruta = ruta;
    }
}
