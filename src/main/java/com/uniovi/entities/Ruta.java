package com.uniovi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="ruta")
public class Ruta {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre_linea;
    private String origen;
    private String destino;

    public Ruta(String linea, String origen, String destino){
        super();
        this.nombre_linea =linea;
        this.origen=origen;
        this.destino=destino;
    }

    public Ruta(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre_linea() {
        return nombre_linea;
    }

    public void setNombre_linea(String linea) {
        this.nombre_linea = linea;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
