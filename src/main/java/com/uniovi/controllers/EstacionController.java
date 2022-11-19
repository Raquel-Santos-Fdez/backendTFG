package com.uniovi.controllers;

import com.uniovi.entities.Estacion;
import com.uniovi.entities.Horario;
import com.uniovi.services.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EstacionController {

    @Autowired
    private EstacionService estacionService;

    @RequestMapping(value = "/estaciones")
    public List<Estacion> getEstaciones() {
        List<Estacion> estaciones= estacionService.getEstaciones();
        return estaciones;
    }

    @RequestMapping(value = "/horarios/{rutaId}/{estacionId}")
    public List<Horario> findHorarioByRutaEstacion(@PathVariable String rutaId, @PathVariable String estacionId) {
        return estacionService.findHorarioByRutaEstacion(rutaId, estacionId);
    }

}
