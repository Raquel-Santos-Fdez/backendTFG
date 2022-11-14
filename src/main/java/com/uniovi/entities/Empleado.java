package com.uniovi.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uniovi.validators.ArgumentValidator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "email", "dni"})})
public class Empleado {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String dni;

    @Enumerated(EnumType.STRING)
    private Rol role;

    private String password;

    private int nDiasLibres;

    public enum Rol {
        MAQUINISTA, REVISOR, ADMIN
    }

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Jornada> jornadas;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Solicitud> solicitudes = new HashSet<>();

    public Empleado(String username, String name, String surname, String email, String dni, String password, Rol rol, int nDiasLibres) {
        super();

        ArgumentValidator.isNotEmpty(username);
        ArgumentValidator.isNotEmpty(name);
        ArgumentValidator.isNotEmpty(surname);
        ArgumentValidator.isNotEmpty(email);
        ArgumentValidator.isNotEmpty(dni);
        ArgumentValidator.isNotEmpty(password);
        ArgumentValidator.isNotNull(rol);

        ArgumentValidator.checkEmail(email);
        ArgumentValidator.checkDni(dni);
        ArgumentValidator.checkPassword(password);

        this.username = username;
        this.name = name;
        this.surname = surname;
        this.nDiasLibres = nDiasLibres;
        this.email = email;
        this.dni = dni;
        this.role = rol;
        this.password = password;

    }

    public Empleado() {
        this.nDiasLibres = 100;

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
