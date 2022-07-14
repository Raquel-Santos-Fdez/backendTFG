package com.uniovi.controllers;

import com.uniovi.entities.Route;
import com.uniovi.entities.Route_stop;
import com.uniovi.services.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RutaController {

    @Autowired
    private RutaService rutaService;

    @RequestMapping(value = "/routes")
    public List<Route> getRoutes() {
        return rutaService.getRoutes();
    }

    @RequestMapping(value = "/route/{origenId}/{destinoId}")
    public List<Route_stop> findRoutesByStops(@PathVariable String origenId, @PathVariable String destinoId) {
        return rutaService.findRutaByEstaciones(origenId, destinoId);
    }
}
