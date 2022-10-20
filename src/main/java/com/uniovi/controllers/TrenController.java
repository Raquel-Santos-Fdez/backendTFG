package com.uniovi.controllers;

import com.uniovi.entities.Incidencia;
import com.uniovi.entities.Tren;
import com.uniovi.services.IncidenciaService;
import com.uniovi.services.TrenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TrenController {

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private TrenService trenService;

    @RequestMapping(value="/tren/getIncidenciasPending/{id}")
    public List<Incidencia> getIncidenciasPending(@PathVariable Long id){
        List<Incidencia> incidencias= incidenciaService.getIncidenciasPending(id);
        return incidencias;
    }

    @RequestMapping(value="/tren/findTrenById/{id}")
    public Tren findTrenById(@PathVariable Long id){
        return trenService.findTrenById(id);
    }

    @RequestMapping(value="/tren/findAll")
    public List<Tren> findAll(){
        return trenService.findAll();
    }

    @PostMapping(value="/tren/addIncidencia")
    public void addInicidencia(@RequestBody Incidencia incidencia){
        incidenciaService.addInicidencia(incidencia);
    }



}
