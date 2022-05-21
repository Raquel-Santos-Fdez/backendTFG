package com.uniovi.services;

import com.uniovi.entities.Employee;
import com.uniovi.entities.Estacion;
import com.uniovi.repositories.EstacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstacionesService {

    @Autowired
    public EstacionesRepository estacionesRepository;

    public void addEstacion(Estacion estacion) {
        estacionesRepository.save(estacion);
    }

    public List<Estacion> getEstaciones() {
        List<Estacion> estaciones=new ArrayList<>();
        estacionesRepository.findAll().forEach(estaciones::add);
        return estaciones;
    }
}
