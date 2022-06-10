package com.uniovi.entities;

import javax.persistence.Entity;

@Entity
public class SolicitudIntercambio extends Solicitud{

    public String fechaDescanso;

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
