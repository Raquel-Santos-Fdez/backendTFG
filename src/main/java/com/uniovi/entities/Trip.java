package com.uniovi.entities;

import javax.persistence.*;

@Entity
public class Trip {

    @Id
    private String trip_id;

    @ManyToOne
    private Ruta ruta;

    public Trip(String trip_id, Ruta ruta) {
        super();
        this.trip_id = trip_id;
        this.ruta = ruta;
    }

    public Trip() {

    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public Ruta getRoute() {
        return ruta;
    }

    public void setRoute(Ruta ruta) {
        this.ruta = ruta;
    }
}
