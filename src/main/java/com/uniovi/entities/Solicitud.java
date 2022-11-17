package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.uniovi.util.validators.ArgumentValidator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,  property="type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SolicitudIntercambio.class, name="solicitudIntercambio"),
        @JsonSubTypes.Type(value = SolicitudSimple.class, name="solicitudSimple"),
        @JsonSubTypes.Type(value = SolicitudVacaciones.class, name="solicitudVacaciones")
})
public abstract class Solicitud {

    public enum EstadoSolicitud{
        PENDIENTE, ACEPTADA, RECHAZADA, REASIGNADA
    }

    public enum MotivoAusencia{
        LICENCIA, OTRO_MOTIVO, FORMACION, VISITA_MEDICA, VACACIONES
    }

    @Id
    @GeneratedValue
    private Long id;

    private Date fecha;

    @Enumerated(EnumType.STRING)
    private MotivoAusencia motivo;

    @ManyToOne
    private Empleado empleado;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estado=EstadoSolicitud.PENDIENTE;

    public  Solicitud(Date fecha, MotivoAusencia motivo, Empleado empleado) {
        super();
        ArgumentValidator.isNotNull(fecha);
        ArgumentValidator.isNotNull(motivo);
        ArgumentValidator.isNotNull(empleado);

        this.fecha = fecha;
        this.motivo = motivo;
        this.empleado = empleado;
        this.estado = EstadoSolicitud.PENDIENTE;
    }

    public Solicitud(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date date) {
        this.fecha = date;
    }

    public MotivoAusencia getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoAusencia motivo) {
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
