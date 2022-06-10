package com.uniovi.entities;

import javax.persistence.*;

@Entity (name="tren")
public class Tren {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne (mappedBy = "tren", cascade = CascadeType.ALL)
    private Tarea tarea;


}
