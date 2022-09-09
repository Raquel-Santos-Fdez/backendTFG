package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Solicitud {

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

    public Solicitud(){

    }

    public Solicitud(String fecha, String motivo){
        this.fecha = fecha;
        this.motivo=motivo;
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
