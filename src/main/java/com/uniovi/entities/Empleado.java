package com.uniovi.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Empleado {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String name;
    private String surname;
    private String email;
    private String dni;

    @Enumerated(EnumType.STRING)
    private Rol role;

    private String password;

    private int nDiasLibres;

    public enum Rol {
        MAQUINISTA, REVISOR, ADMIN
    }

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
//    @JsonIgnoreProperties(value = "empleado", allowSetters = true)
    @JsonIgnore
    private Set<Jornada> jornadas;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.MERGE)
    @JsonIgnore
    private Set<Solicitud> solicitudes = new HashSet<>();


    public Empleado(String username, String name, String surname, int nDiasLibres) {
        super();
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.nDiasLibres=nDiasLibres;
//        this.jornadas=jornadas;

    }

    public Empleado() {

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

    public Rol getRole() {
        return role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Set<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(Set<Jornada> jornadas) {
        this.jornadas = jornadas;
    }

    public Set<Solicitud> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(Set<Solicitud> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public int getnDiasLibres() {
        return nDiasLibres;
    }

    public void setnDiasLibres(int nDiasLibres) {
        this.nDiasLibres = nDiasLibres;
    }
}
