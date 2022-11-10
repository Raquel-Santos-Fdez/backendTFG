package com.uniovi.entities;

import javax.persistence.Entity;

@Entity
public class SolicitudVacaciones extends Solicitud{

    private String fechaFinVacaciones;

    public SolicitudVacaciones(String fecha, MotivoAusencia motivo, Empleado empleado) {
        super( fecha, motivo, empleado);
    }

    public SolicitudVacaciones(String fecha, MotivoAusencia motivo, Empleado empleado, String fechaFinVacaciones) {
        super( fecha, motivo, empleado);
        this.fechaFinVacaciones=fechaFinVacaciones;
    }

    public SolicitudVacaciones() {
        super();
    }

    public String getFechaFinVacaciones() {
        return fechaFinVacaciones;
    }

    public void setFechaFinVacaciones(String fechaFinVacaciones) {
        this.fechaFinVacaciones = fechaFinVacaciones;
    }
}
