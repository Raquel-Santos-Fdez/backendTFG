package com.uniovi.entities;

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
    private Estacion estacion;

    @ManyToOne
    private Ruta ruta;

    private int orderParada;

    public Route_stop() {

    }

    public Route_stop(int orderParada, Estacion estacion, Ruta ruta ) {
        super();
        this.estacion = estacion;
        this.ruta = ruta;
        this.orderParada = orderParada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estacion getStop() {
        return estacion;
    }

    public void setStop(Estacion estacion) {
        this.estacion = estacion;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public int getOrderParada() {
        return orderParada;
    }

    public void setOrderParada(int orderParada) {
        this.orderParada = orderParada;
    }
}
