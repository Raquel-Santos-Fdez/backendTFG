package com.uniovi.entities;

import javax.persistence.*;

@Entity(name="stop")
public class Stop {

    @Id
    public String stop_id;
    public String stop_name;
    public double stop_lat;
    public double stop_lon;

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
}
