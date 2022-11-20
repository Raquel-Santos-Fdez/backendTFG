package com.uniovi.entities;

import com.uniovi.util.validators.ArgumentValidator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Estacion {

    @Id
    private String id;
    @Column(unique = true)
    private String nombre;
    private double latitud;
    private double longitud;

    @OneToMany(mappedBy = "estacion", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Tarea_estacion> tareas=new HashSet<>();

    @OneToMany(mappedBy = "estacion")
    @JsonIgnore
    private Set<Ruta_estacion> rutas =new HashSet<>();


    public Estacion(){

    }

    public Estacion(String id, String nombre, double latitud, double longitud){
        super();
        ArgumentValidator.isNotEmpty(id);
        ArgumentValidator.isNotEmpty(nombre);
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud =longitud;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Ruta_estacion> getRutas() {
        return rutas;
    }

    public void setRutas(Set<Ruta_estacion> routes) {
        this.rutas = routes;
    }

    public Set<Tarea_estacion> getTareas() {
        return tareas;
    }

    public void setTareas(Set<Tarea_estacion> tareas) {
        this.tareas = tareas;
    }
}
