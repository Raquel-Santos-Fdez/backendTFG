package com.uniovi.entities;

import javax.persistence.*;

@Entity(name="stop_times")
public class Stop_time {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    public Trip trip;

    @ManyToOne
    public Stop stop;

    public String arrival_time;
    public String departure_time;
    public String stop_sequence;

    public Stop_time(){

    }

    public Stop_time(Trip trip, Stop stop, String arrival_time, String departure_time, String stop_sequence){
        super();
        this.trip = trip;
        this.stop = stop;
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

    public Stop getStop() {
        return stop;
    }

    public void setStop(Stop stop) {
        this.stop = stop;
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
}
