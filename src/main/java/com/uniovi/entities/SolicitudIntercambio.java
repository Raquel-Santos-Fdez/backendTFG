package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class SolicitudIntercambio extends Solicitud{

    public String fechaDescanso;

    @ManyToOne
    private Empleado nuevoEmpleado;

    public SolicitudIntercambio(){

    }

    public SolicitudIntercambio(Long id, String fecha, String motivo, Empleado empleado, EstadoSolicitud estado, String fechaDescanso, Empleado nuevoEmpleado) {
        super(id, fecha, motivo, empleado, estado);
        this.fechaDescanso = fechaDescanso;
        this.nuevoEmpleado = nuevoEmpleado;
    }

    public String getFechaDescanso() {
        return fechaDescanso;
    }

    public void setFechaDescanso(String fechaDescanso) {
        this.fechaDescanso = fechaDescanso;
    }

    public Empleado getNuevoEmpleado() {
        return nuevoEmpleado;
    }

    public void setNuevoEmpleado(Empleado nuevoEmpleado) {
        this.nuevoEmpleado = nuevoEmpleado;
    }

}
