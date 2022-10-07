package com.uniovi.controllers;

import com.uniovi.entities.*;
import com.uniovi.services.JornadaService;
import com.uniovi.services.TareaService;
import com.uniovi.services.TareaStopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class JornadaController {

    @Autowired
    private JornadaService jornadaService;

    @Autowired
    private TareaStopsService tareaStopsService;

    @Autowired
    private TareaService tareaService;

   //Get

    @RequestMapping(value="/jornada/consultar/{date}/{id}")
    public List<Tarea> getTareasByDateAndEmpleoyee(@PathVariable Date date, @PathVariable String id){
        return jornadaService.getJornadaByDateAndEmpleoyee(Long.parseLong(id), date);
    }


    @RequestMapping(value="/tareaStop/{id}")
    public Estacion findStopByTarea(@PathVariable Long id){
        return jornadaService.findStopByTarea(id);
    }

    @RequestMapping(value="/jornada/findByEmpleado/{id}")
    public List<Jornada> findJornadaByEmpleado(@PathVariable Long id){
        return jornadaService.findJornadaByEmpleado(id);
    }

    @RequestMapping(value="/jornada/findByDate/{date}")
    public List<Jornada> findJornadaByDate(@PathVariable Date date){
        return jornadaService.findJornadaByDate(date);

    }

    @RequestMapping(value="/jornada/findJornadaByDateEmpleado/{date}/{id}")
    public List<Jornada> findJornadaByDateEmployee(@PathVariable Date date, @PathVariable Long id){
        return jornadaService.findJornadaByDateEmployee(date, id);
    }

    @RequestMapping(value="/tarea/{id}")
    public Tarea findTareaById(@PathVariable Long id){
        return tareaService.findTareaById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value="jornada/reasignar")
    public void reasignar(@RequestBody SolicitudIntercambio solicitud){
        jornadaService.reasignar(solicitud);
    }

    //Post

    @PostMapping("/jornada/consultar")
    public List<Tarea> getJornadaByDateAndEmpleoyee(@RequestBody Date date, @RequestBody Long idEmployee){
        return jornadaService.getJornadaByDateAndEmpleoyee(idEmployee, date);
    }

    @PostMapping(value="/jornada/solicitar-intercambio")
    public void addSolicitudIntercambio(@RequestBody Solicitud solicitud){
        jornadaService.addSolicitudIntercambio(solicitud);
    }

    @PostMapping(value="/tarea/addTarea")
    public Tarea addTarea(@RequestBody Tarea tarea){
        return tareaService.addTarea(tarea);
    }

    @PostMapping(value="/jornada/addJornada")
    public Jornada addJornada(@RequestBody Jornada jornada){
        return jornadaService.addJornada(jornada);
    }

    @PostMapping(value="/tareaStop/addNuevaTareaStop")
    public void addNuevaTareaStop(@RequestBody Tarea_stops tarea_stops ){
        tareaStopsService.addNuevaTareaStop(tarea_stops);
    }
}
