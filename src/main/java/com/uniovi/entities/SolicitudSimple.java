package com.uniovi.entities;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class SolicitudSimple extends Solicitud{

    public SolicitudSimple(Date fecha, MotivoAusencia motivo, Empleado empleado) {
        super(fecha, motivo, empleado);
    }

    public SolicitudSimple() {
        super();
    }


}
