package com.uniovi.entities;

//import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique=true)
    private String username;
    private String name;
    private String surname;

    private String role;

    private String password;
    @Transient //propiedad que no se almacena e la tabla.
    private String passwordConfirm;

    @OneToMany(mappedBy="employee")
    private Set<Jornada> jornadas;

    public Employee(String username, String name, String surname){
        super();
        this.username=username;
        this.name=name;
        this.surname=surname;
//        this.jornadas=jornadas;

    }

    public Employee() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
