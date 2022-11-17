package com.uniovi.entities;

import com.uniovi.util.validators.ArgumentValidator;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class SolicitudIntercambio extends Solicitud{

    private Date fechaDescanso;

    @ManyToOne
    private Empleado nuevoEmpleado;



    public SolicitudIntercambio( Date fecha, MotivoAusencia motivo, Empleado empleado, Date fechaDescanso, Empleado nuevoEmpleado) {
        super( fecha, motivo, empleado);
        ArgumentValidator.isNotNull(fechaDescanso);
        this.fechaDescanso = fechaDescanso;
        this.nuevoEmpleado = nuevoEmpleado;
    }

    public SolicitudIntercambio() {
        super();
    }

    public Date getFechaDescanso() {
        return fechaDescanso;
    }

    public void setFechaDescanso(Date fechaDescanso) {
        this.fechaDescanso = fechaDescanso;
    }

    public Empleado getNuevoEmpleado() {
        return nuevoEmpleado;
    }

    public void setNuevoEmpleado(Empleado nuevoEmpleado) {
        this.nuevoEmpleado = nuevoEmpleado;
    }


}
