package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Route_stop {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonManagedReference
    private Stop stop;

    @ManyToOne
    @JsonManagedReference
    private Route route;

    private int orderParada;

    public Route_stop() {

    }

    public Route_stop(Stop stop, Route route, int orderParada) {
        super();
        this.stop = stop;
        this.route = route;
        this.orderParada = orderParada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getOrderParada() {
        return orderParada;
    }

    public void setOrderParada(int orderParada) {
        this.orderParada = orderParada;
    }
}
