package com.uniovi.controllers;

import com.uniovi.entities.Route_stop;
import com.uniovi.entities.Ruta;
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

    @RequestMapping(value = "/rutas")
    public List<Ruta> getRutas() {
        return rutaService.getRutas();
    }

    @RequestMapping(value = "/ruta/{origenId}/{destinoId}")
    public List<Route_stop> findRutaByEstaciones(@PathVariable String origenId, @PathVariable String destinoId) {
        return rutaService.findRutaByEstaciones(origenId, destinoId);
    }

    @RequestMapping(value = "/rutas/{id}")
    public Ruta getRutaById(@PathVariable String id) {
        return rutaService.getRutaById(id);
    }
}
