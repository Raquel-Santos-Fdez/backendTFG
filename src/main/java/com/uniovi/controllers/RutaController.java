package com.uniovi.controllers;

import com.uniovi.entities.Route;
import com.uniovi.entities.Route_stop;
import com.uniovi.entities.Stop;
import com.uniovi.entities.Stop_time;
import com.uniovi.services.EstacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RutaController {

    @Autowired
    private EstacionesService estacionesService;

    @RequestMapping(value = "/routes")
    public List<Route> getRoutes() {
        return estacionesService.getRoutes();
    }

    @RequestMapping(value = "/route/{origenId}/{destinoId}")
    public List<Route_stop> findRoutesByStops(@PathVariable String origenId, @PathVariable String destinoId) {
        List<Route_stop> rutas=estacionesService.findRoutesByStops(origenId, destinoId);
        return rutas;
    }
}
