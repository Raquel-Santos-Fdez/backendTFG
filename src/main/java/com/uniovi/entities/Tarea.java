package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tarea {

    @Id
    @GeneratedValue
    private Long id;

    private String descripcion;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime horaSalida;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime horaFin;

    private int anden;

    @OneToOne
    @JoinColumn(name = "tren_id")
    private Tren tren;

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="tarea_id")
    private Set<Tarea_stops> stops = new HashSet<>();

    public Tarea() {

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

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }


}
