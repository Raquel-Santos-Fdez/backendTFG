package com.uniovi.entities;

import com.uniovi.util.validators.ArgumentValidator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Jornada {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Empleado empleado;

    private Date date;

    private boolean isDiaLibre;

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="jornada_id")
    private Set<Tarea> tareas = new HashSet<>();


    public Jornada(){

    }

    public Jornada( Date date, Empleado empleado){
        super();
        ArgumentValidator.isNotNull(date);
        ArgumentValidator.isNotNull(empleado);
        this.date=date;
        this.empleado=empleado;
    }

    public Jornada( Date date, Empleado empleado, boolean isDiaLibre){
        this(date, empleado);
        this.isDiaLibre=isDiaLibre;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Tarea> getTareas() {
        return new HashSet<Tarea>(tareas);
    }

    public void setTareas(Set<Tarea> tareas) {
        this.tareas = tareas;
    }

    public boolean isDiaLibre() {
        return isDiaLibre;
    }

    public void setDiaLibre(boolean diaLibre) {
        isDiaLibre = diaLibre;
    }
}
