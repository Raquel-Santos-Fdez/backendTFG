package com.uniovi.controllers;

import com.uniovi.entities.Jornada;
import com.uniovi.entities.Tarea;
import com.uniovi.repositories.JornadaRepository;
import com.uniovi.services.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class JornadaController {

    @Autowired
    private JornadaService jornadaService;

    @PostMapping("/jornada/consultar")
    public List<Tarea> getJornadaByDateAndEmpleoyee(@RequestBody Date date){
        return jornadaService.getJornadaByDateAndEmpleoyee(Long.parseLong("1"), date);
    }

    @RequestMapping(value="/jornada/consultar/{date}/{id}")
    public List<Tarea> getTareasByDateAndEmpleoyee(@PathVariable Date date, @PathVariable String id){
        List<Tarea> tareas=jornadaService.getJornadaByDateAndEmpleoyee(Long.parseLong(id), date);
        return tareas;
    }


}
