package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Trip {

    @Id
    private String id;

    @ManyToOne
    private Ruta ruta;

    public Trip(String id, Ruta ruta) {
        super();
        this.id = id;
        this.ruta = ruta;
    }

    public Trip() {

    }

    public String getId() {
        return id;
    }

    public void setId(String trip_id) {
        this.id = trip_id;
    }

    public Ruta getRoute() {
        return ruta;
    }

    public void setRoute(Ruta ruta) {
        this.ruta = ruta;
    }
}
