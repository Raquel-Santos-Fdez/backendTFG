package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uniovi.util.validators.ArgumentValidator;

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
    private Set<Tarea_stops> tareas=new HashSet<>();

    @OneToMany(mappedBy = "estacion")
    @JsonIgnore
    private Set<Route_stop> routes=new HashSet<>();


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

    public Set<Route_stop> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route_stop> routes) {
        this.routes = routes;
    }

    public Set<Tarea_stops> getTareas() {
        return tareas;
    }

    public void setTareas(Set<Tarea_stops> tareas) {
        this.tareas = tareas;
    }
}
