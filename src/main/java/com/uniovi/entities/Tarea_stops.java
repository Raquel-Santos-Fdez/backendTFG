package com.uniovi.entities;

import com.uniovi.util.validators.ArgumentValidator;

import javax.persistence.*;

@Entity(name="tarea_stops")
public class Tarea_stops {

    public enum Situacion{
        INICIO, FINAL
    }

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Situacion situacion=Situacion.INICIO;

    @ManyToOne
    private Estacion estacion;

    public Tarea_stops(){

    }

    public Tarea_stops(Situacion situacion, Estacion estacion, Tarea tarea) {
        ArgumentValidator.isNotNull(situacion);
        ArgumentValidator.isNotNull(estacion);
        ArgumentValidator.isNotNull(tarea);
        this.situacion = situacion;
        this.estacion = estacion;
//        this.tarea = tarea;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Situacion getSituacion() {
        return situacion;
    }

    public void setSituacion(Situacion situacion) {
        this.situacion = situacion;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

}
