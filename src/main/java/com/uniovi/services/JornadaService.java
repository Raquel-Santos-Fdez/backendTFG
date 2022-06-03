package com.uniovi.services;

import com.uniovi.entities.Jornada;
import com.uniovi.entities.Tarea;
import com.uniovi.repositories.JornadaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JornadaService {

    @Autowired
    public JornadaRepository jornadaRepository;


    public List<Tarea> getJornadaByDateAndEmpleoyee(Long id, Date date) {
        List<Tarea> jornadas=jornadaRepository.findByDateAndEmployee(id, new java.sql.Date(date.getTime()));
        return jornadas;
    }
}
