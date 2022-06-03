package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class Tarea {

    @Id
    @GeneratedValue
    private Long id;

    private String descripcion;

    private LocalTime inicioTarea;
    private LocalTime finalTarea;

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

    public LocalTime getInicioTarea() {
        return inicioTarea;
    }

    public void setInicioTarea(LocalTime inicioTarea) {
        this.inicioTarea = inicioTarea;
    }

    public LocalTime getFinalTarea() {
        return finalTarea;
    }

    public void setFinalTarea(LocalTime finalTarea) {
        this.finalTarea = finalTarea;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void _setJornada(Jornada jornada) {
        this.jornada = jornada;
    }
}
