package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,  property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=SolicitudIntercambio.class, name="solicitudIntercambio"),
        @JsonSubTypes.Type(value = SolicitudSimple.class, name="solicitudSimple")
})
public abstract class Solicitud {

    public enum EstadoSolicitud{
        PENDIENTE, ACEPTADA, RECHAZADA, REASIGNADA
    }

    @Id
    @GeneratedValue
    private Long id;

    private String fecha;
    private String motivo;

    @ManyToOne
    private Empleado empleado;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado=EstadoSolicitud.PENDIENTE;

    protected Solicitud(Long id, String fecha, String motivo, Empleado empleado, EstadoSolicitud estado) {
        this.id = id;
        this.fecha = fecha;
        this.motivo = motivo;
        this.empleado = empleado;
        this.estado = estado;
    }
    protected Solicitud(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String date) {
        this.fecha = date;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
