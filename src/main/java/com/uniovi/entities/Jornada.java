package com.uniovi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    private Employee employee;
    private Date date;

    @OneToMany(mappedBy = "jornada", cascade = CascadeType.ALL)
    @JsonManagedReference (value = "jornada-tarea")
    private Set<Tarea> tareas = new HashSet<>();


    public Jornada(){

    }

    public Jornada( Date date){
        this.date=date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Tarea> getTarea() {
        return new HashSet<Tarea>(tareas);
    }

    public void setTarea(Set<Tarea> tareas) {
        this.tareas = tareas;
    }
}
