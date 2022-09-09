package com.uniovi.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class SolicitudSimple extends  Solicitud{

    public SolicitudSimple() {
        super();
    }
}
