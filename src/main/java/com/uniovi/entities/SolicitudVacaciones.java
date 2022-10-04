package com.uniovi.entities;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class SolicitudVacaciones extends Solicitud{

    private Date fechaFinVacaciones;

    public SolicitudVacaciones(Long id, String fecha, String motivo, Empleado empleado, EstadoSolicitud estado) {
        super(id, fecha, motivo, empleado, estado);
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
