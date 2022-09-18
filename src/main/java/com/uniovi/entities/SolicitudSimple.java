package com.uniovi.entities;

import javax.persistence.Entity;

@Entity
public class SolicitudSimple extends Solicitud{

    public SolicitudSimple(Long id, String fecha, String motivo, Empleado empleado, EstadoSolicitud estado) {
        super(id, fecha, motivo, empleado, estado);
    }

    public SolicitudSimple() {
        super();
    }


}
