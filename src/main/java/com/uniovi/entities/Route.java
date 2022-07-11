package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Route {

    @Id
    private String route_id;
    private String route_short_name;
    private String route_long_name;

    @OneToMany(mappedBy = "route")
    @JsonBackReference
    private Set<Route_stop> stops=new HashSet<>();

    public Route(){

    }

    public Route (String route_id, String route_short_name, String route_long_name){
        super();
        this.route_id=route_id;
        this.route_short_name=route_short_name;
        this.route_long_name=route_long_name;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getRoute_short_name() {
        return route_short_name;
    }

    public void setRoute_short_name(String route_short_name) {
        this.route_short_name = route_short_name;
    }

    public String getRoute_long_name() {
        return route_long_name;
    }

    public void setRoute_long_name(String route_long_name) {
        this.route_long_name = route_long_name;
    }

    public Set<Route_stop> getStops() {
        return stops;
    }

    public void setStops(Set<Route_stop> stops) {
        this.stops = stops;
    }


}
