package com.uniovi.controllers;

import com.uniovi.entities.Incidencia;
import com.uniovi.entities.SolicitudIntercambio;
import com.uniovi.services.IncidenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrenController {

    @Autowired
    private IncidenciasService incidenciasService;

    @RequestMapping(value="/tren/getIncidenciasPending/{id}")
    public List<Incidencia> getIncidenciasPending(@PathVariable Long id){
        List<Incidencia> incidencias=incidenciasService.getIncidenciasPending(id);
        return incidencias;
    }

    @PostMapping(value="/tren/addIncidencia")
    public void addInicidencia(@RequestBody Incidencia incidencia){
        System.out.println("ENTRAAAAAAAAA");
        incidenciasService.addInicidencia(incidencia);
    }
}
