package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Stop {

    @Id
    public String stop_id;
    public String stop_name;
    public double stop_lat;
    public double stop_lon;

    @OneToMany(mappedBy = "stop", cascade = CascadeType.ALL)
    @JsonManagedReference  (value="stopT-stop")
    private Set<Tarea_stops> tareas=new HashSet<>();

    @OneToMany(mappedBy = "stop")
    @JsonBackReference
    private Set<Route_stop> routes=new HashSet<>();


    public Stop(){

    }

    public Stop(String stop_id, String stop_name, double stop_lat, double stop_long){
        super();
        this.stop_id=stop_id;
        this.stop_name=stop_name;
        this.stop_lat=stop_lat;
        this.stop_lon =stop_long;
    }


    public String getStop_id() {
        return stop_id;
    }

    public void setStop_id(String stop_id) {
        this.stop_id = stop_id;
    }

    public double getStop_lat() {
        return stop_lat;
    }

    public void setStop_lat(double stop_lat) {
        this.stop_lat = stop_lat;
    }

    public double getStop_lon() {
        return stop_lon;
    }

    public void setStop_lon(double stop_long) {
        this.stop_lon = stop_long;
    }

    public String getStop_name() {
        return stop_name;
    }

    public void setStop_name(String stop_name) {
        this.stop_name = stop_name;
    }

    public Set<Tarea_stops> getTareas() {
        return tareas;
    }

    public void setTareas(Set<Tarea_stops> tareas) {
        this.tareas = tareas;
    }

    public Set<Route_stop> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route_stop> routes) {
        this.routes = routes;
    }

}
