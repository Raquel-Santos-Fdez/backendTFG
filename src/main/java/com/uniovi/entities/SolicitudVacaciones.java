package com.uniovi.entities;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class SolicitudVacaciones extends Solicitud{

    private Date fechaFinVacaciones;

    public SolicitudVacaciones(Date fecha, MotivoAusencia motivo, Empleado empleado) {
        super( fecha, motivo, empleado);
    }

    public SolicitudVacaciones(Date fecha, MotivoAusencia motivo, Empleado empleado, Date fechaFinVacaciones) {
        super( fecha, motivo, empleado);
        this.fechaFinVacaciones=fechaFinVacaciones;
    }

    public SolicitudVacaciones() {
        super();
    }

    public Date getFechaFinVacaciones() {
        return fechaFinVacaciones;
    }

    public void setFechaFinVacaciones(Date fechaFinVacaciones) {
        this.fechaFinVacaciones = fechaFinVacaciones;
    }
}
