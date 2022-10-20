package com.uniovi.controllers;

import com.uniovi.entities.Estacion;
import com.uniovi.entities.Stop_time;
import com.uniovi.services.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/stopTimes")
    public List<Stop_time> getStopTimes() {
        return estacionService.getStopTimes();
    }



    @RequestMapping(value = "/stopTimes/{rutaId}/{stopId}")
    public List<Stop_time> findStopTimeByRouteStop(@PathVariable String rutaId,@PathVariable String stopId) {
        return estacionService.findStopTimeByRouteStop(rutaId, stopId);
    }

}
