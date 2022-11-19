package com.uniovi.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity(name="horario")
public class Horario {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Trayecto trayecto;

    @ManyToOne
    private Estacion estacion;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime hora_llegada;
    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    private LocalTime hora_salida;

    public Horario(){

    }

    public Horario(Trayecto trayecto, Estacion estacion, LocalTime hora_llegada, LocalTime hora_salida){
        super();
        this.trayecto = trayecto;
        this.estacion = estacion;
        this.hora_llegada = hora_llegada;
        this.hora_salida = hora_salida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trayecto getTrayecto() {
        return trayecto;
    }

    public void setTrayecto(Trayecto trayecto) {
        this.trayecto = trayecto;
    }

    public Estacion getStop() {
        return estacion;
    }

    public void setStop(Estacion estacion) {
        this.estacion = estacion;
    }

    public LocalTime getHora_llegada() {
        return hora_llegada;
    }

    public void setHora_llegada(LocalTime arrival_time) {
        this.hora_llegada = arrival_time;
    }

    public LocalTime getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(LocalTime departure_time) {
        this.hora_salida = departure_time;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }
}
