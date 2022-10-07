package com.uniovi.controllers;

import com.uniovi.entities.Estacion;
import com.uniovi.entities.Ruta;
import com.uniovi.entities.Stop_time;
import com.uniovi.services.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private EstacionService estacionService;

    @RequestMapping("/")
    @CrossOrigin(origins="*", maxAge = 3600)
    public String inicio(){
        return "inicio";
    }

    @RequestMapping(value = "/stops")
    public List<Estacion> getStops() {
        return estacionService.getStops();
    }

    @RequestMapping(value = "/stopTimes")
    public List<Stop_time> getStopTimes() {
        return estacionService.getStopTimes();
    }

    @RequestMapping(value = "/rutas/{id}")
    public Ruta getRutaById(@PathVariable String id) {
        return estacionService.getRouteById(id);
    }

    @RequestMapping(value = "/stopTimes/{rutaId}/{stopId}")
    public List<Stop_time> findStopTimeByRouteStop(@PathVariable String rutaId,@PathVariable String stopId) {
        return estacionService.findStopTimeByRouteStop(rutaId, stopId);
    }

}
