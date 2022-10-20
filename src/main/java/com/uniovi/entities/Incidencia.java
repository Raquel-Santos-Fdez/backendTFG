package com.uniovi.entities;

import com.uniovi.validators.ArgumentValidator;

import javax.persistence.*;

@Entity
public class Incidencia {

    public enum EstadoIncidencia{
        PENDIENTE, RESUELTA
    }

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private EstadoIncidencia estado=EstadoIncidencia.PENDIENTE;

    private String descripcion;

    @ManyToOne
    private Tren tren;

    public Incidencia(){

    }

    public Incidencia(String descripcion, Tren tren){
        super();
        ArgumentValidator.isNotEmpty(descripcion);
        ArgumentValidator.isNotNull(tren);

        this.estado=EstadoIncidencia.PENDIENTE;
        this.descripcion=descripcion;
        this.tren=tren;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoIncidencia getEstado() {
        return estado;
    }

    public void setEstado(EstadoIncidencia estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Tren getTren() {
        return tren;
    }

    public void setTren(Tren tren) {
        this.tren = tren;
    }
}
