package com.uniovi.entities;

import javax.persistence.*;

@Entity(name = "trip")
public class Trip {

    @Id
    private String trip_id;

    @ManyToOne
    private Route route;

    public Trip(String trip_id, Route route) {
        super();
        this.trip_id = trip_id;
        this.route = route;
    }

    public Trip() {

    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        this.trip_id = trip_id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
