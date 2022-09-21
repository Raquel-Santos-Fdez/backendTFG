package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("solicitudSimple")
public class SolicitudSimple extends Solicitud{

    public SolicitudSimple(Long id, String fecha, String motivo, Empleado empleado, EstadoSolicitud estado) {
        super(id, fecha, motivo, empleado, estado);
    }

    public SolicitudSimple() {
        super();
    }


}
