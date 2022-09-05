package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class SolicitudIntercambio extends Solicitud{

    public String fechaDescanso;

    @ManyToOne
    private Empleado nuevoEmpleado;

    public SolicitudIntercambio(){
        super();
    }

    public SolicitudIntercambio(String fechaDescanso) {
        this.fechaDescanso = fechaDescanso;
    }

    public SolicitudIntercambio(String fecha, String motivo, String fechaDescanso) {
        super(fecha, motivo);
        this.fechaDescanso = fechaDescanso;
    }

    public String getFechaDescanso() {
        return fechaDescanso;
    }

    public void setFechaDescanso(String fechaDescanso) {
        this.fechaDescanso = fechaDescanso;
    }
}
