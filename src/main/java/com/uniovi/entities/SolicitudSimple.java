package com.uniovi.entities;

import javax.persistence.Entity;

@Entity
public class SolicitudSimple extends Solicitud{

    public SolicitudSimple( String fecha, String motivo, Empleado empleado) {
        super(fecha, motivo, empleado);
    }

    public SolicitudSimple() {
        super();
    }


}
