package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

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

    @ManyToOne (cascade = CascadeType.ALL)
    @JsonBackReference (value="stopT-stop")
    private Stop stop;

    @ManyToOne (cascade = CascadeType.ALL)
    @JsonBackReference (value="stopT-tarea")
    private Tarea tarea;

    public Tarea_stops(){

    }

    public Tarea_stops(Situacion situacion, Stop stop, Tarea tarea) {
        this.situacion = situacion;
        this.stop = stop;
        this.tarea = tarea;
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

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
}
