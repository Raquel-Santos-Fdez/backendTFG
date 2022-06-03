package com.uniovi.services;

import com.uniovi.entities.Employee;
import com.uniovi.entities.Jornada;
import com.uniovi.repositories.EmployeeRepository;
import com.uniovi.repositories.JornadaRepository;
import com.uniovi.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

//@Service
public class LoadJornadaService {

    @Autowired
    private JornadaRepository jornadaRepository;

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostConstruct
    public void init() {

        Employee e1=new Employee("rsfernandez", "Raquel", "Santos");
        Jornada j1=new Jornada();


    }
}
