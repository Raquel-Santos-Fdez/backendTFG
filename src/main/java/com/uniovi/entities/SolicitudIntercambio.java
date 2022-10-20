package com.uniovi.entities;

import com.uniovi.validators.ArgumentValidator;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class SolicitudIntercambio extends Solicitud{

    private String fechaDescanso;

    @ManyToOne
    private Empleado nuevoEmpleado;



    public SolicitudIntercambio( String fecha, String motivo, Empleado empleado, String fechaDescanso, Empleado nuevoEmpleado) {
        super( fecha, motivo, empleado);
        ArgumentValidator.isNotEmpty(fechaDescanso);
        this.fechaDescanso = fechaDescanso;
        this.nuevoEmpleado = nuevoEmpleado;
    }

    public SolicitudIntercambio() {
        super();
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
