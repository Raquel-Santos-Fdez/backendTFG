package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="stop_times")
public class Stop_time {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Trip trip;

    @ManyToOne
    private Estacion estacion;

    private String arrival_time;
    private String departure_time;
    private String stop_sequence;

    public Stop_time(){

    }

    public Stop_time(Trip trip, Estacion estacion, String arrival_time, String departure_time, String stop_sequence){
        super();
        this.trip = trip;
        this.estacion = estacion;
        this.arrival_time=arrival_time;
        this.departure_time=departure_time;
        this.stop_sequence=stop_sequence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Estacion getStop() {
        return estacion;
    }

    public void setStop(Estacion estacion) {
        this.estacion = estacion;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getStop_sequence() {
        return stop_sequence;
    }

    public void setStop_sequence(String stop_sequence) {
        this.stop_sequence = stop_sequence;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }
}
