package com.uniovi.controllers;

import com.uniovi.entities.Jornada;
import com.uniovi.entities.SolicitudIntercambio;
import com.uniovi.entities.Tarea;
import com.uniovi.entities.Tarea_estacion;
import com.uniovi.services.JornadaService;
import com.uniovi.services.TareaService;
import com.uniovi.services.TareaEstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
public class JornadaController {

    @Autowired
    private JornadaService jornadaService;

    @Autowired
    private TareaEstacionService tareaEstacionService;

    @Autowired
    private TareaService tareaService;

   //Get

    @RequestMapping(value="/jornada/consultar/{date}/{id}")
    public List<Tarea> getTareasByFechaEmpleado(@PathVariable Date date, @PathVariable Long id){
        return jornadaService.getTareasByFechaEmpleado(id, date);
    }


    @RequestMapping(value="/jornada/findByEmpleado/{id}")
    public List<Jornada> findJornadaByEmpleado(@PathVariable Long id){
        return jornadaService.findJornadaByEmpleado(id);
    }

    @RequestMapping(value="/jornada/findByDate/{date}")
    public List<Jornada> findJornadaByFecha(@PathVariable Date date){
        return jornadaService.findJornadaByFecha(date);

    }

    @RequestMapping(value="/jornada/findJornadaByDateEmpleado/{date}/{id}")
    public List<Jornada> findJornadaByFechaEmpleado(@PathVariable Date date, @PathVariable Long id){
        return jornadaService.findJornadaByFechaEmpleado(date, id);
    }

    @RequestMapping(value="/tarea/{id}")
    public Tarea findTareaById(@PathVariable Long id){
        return tareaService.findTareaById(id);
    }

    @RequestMapping(value="/jornada/existeTarea/{fecha}/{idEmpleado}/{horaSalida}/{horaFin}")
    public boolean existeTarea(@PathVariable Date fecha, @PathVariable Long idEmpleado, @PathVariable LocalTime horaSalida, @PathVariable LocalTime horaFin){
        return jornadaService.existeTarea(fecha, idEmpleado, horaSalida, horaFin);
    }

    //Put

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value="jornada/reasignar")
    public void reasignar(@RequestBody SolicitudIntercambio solicitud){
        jornadaService.reasignar(solicitud);
    }

    //Post

    @PostMapping(value="/jornada/addJornada")
    public Jornada addJornada(@RequestBody Jornada jornada){
        return jornadaService.addJornada(jornada);
    }

//    @PostMapping(value="/tareaEstacion/addNuevaTareaEstacion")
//    public void addNuevaTareaEstacion(@RequestBody Tarea_estacion tarea_estacion){
//        tareaEstacionService.addNuevaTareaEstacion(tarea_estacion);
//    }
}
