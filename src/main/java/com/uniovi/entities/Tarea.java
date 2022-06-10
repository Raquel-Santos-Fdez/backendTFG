package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tarea {

    @Id
    @GeneratedValue
    private Long id;

    private String descripcion;

    private LocalTime horaSalida;

    private int anden;

    @OneToOne
    @JoinColumn(name="tren_id")
    private Tren tren;

    @OneToMany(mappedBy = "tarea")
    @JsonManagedReference
    private Set<Tarea_stops> stops=new HashSet<>();

    @ManyToOne
    @JsonBackReference
    private Jornada jornada;

    public Tarea(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void _setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getAnden() {
        return anden;
    }

    public void setAnden(int anden) {
        this.anden = anden;
    }

    public Tren getTren() {
        return tren;
    }

    public void setTren(Tren tren) {
        this.tren = tren;
    }

    public Set<Tarea_stops> getStops() {
        return stops;
    }

    public void setStops(Set<Tarea_stops> stops) {
        this.stops = stops;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }
}
