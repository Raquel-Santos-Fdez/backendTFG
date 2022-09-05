package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Estacion {

    @Id
    public String id;
    public String nombre;
    public double latitud;
    public double longitud;

    @OneToMany(mappedBy = "estacion", cascade = CascadeType.ALL)
//    @JsonManagedReference  (value="stopT-estacion")
    @JsonIgnoreProperties(value="estacion")
    private Set<Tarea_stops> tareas=new HashSet<>();

    @OneToMany(mappedBy = "estacion")
    @JsonBackReference
    private Set<Route_stop> routes=new HashSet<>();


    public Estacion(){

    }

    public Estacion(String id, String nombre, double latitud, double longitud){
        super();
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

//    public Set<Tarea_stops> getTareas() {
//        return tareas;
//    }
//
//    public void setTareas(Set<Tarea_stops> tareas) {
//        this.tareas = tareas;
//    }

    public Set<Route_stop> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route_stop> routes) {
        this.routes = routes;
    }

}
