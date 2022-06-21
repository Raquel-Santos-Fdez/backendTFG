package com.uniovi.controllers;

import com.uniovi.entities.*;
import com.uniovi.repositories.JornadaRepository;
import com.uniovi.services.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class JornadaController {

    @Autowired
    private JornadaService jornadaService;

   //Get

    @RequestMapping(value="/jornada/consultar/{date}/{id}")
    public List<Tarea> getTareasByDateAndEmpleoyee(@PathVariable Date date, @PathVariable String id){
        List<Tarea> tareas=jornadaService.getJornadaByDateAndEmpleoyee(Long.parseLong(id), date);
        return tareas;
    }

    @RequestMapping(value="/jornada/ver_solicitudes_vacaciones")
    public List<Solicitud> getAllSolicitudesPendientes(){
        List<Solicitud> solicitudes=jornadaService.getAllSolicitudesPendientes();
        return solicitudes;
    }

    @RequestMapping(value="/tarea_stop/{id}")
    public Stop findStopByTarea(@PathVariable Long id){
        Stop stops=jornadaService.findStopByTarea(id);
        return stops;
    }

    @RequestMapping(value="/jornada/find_others_solicitudes/{id}")
    public List<SolicitudIntercambio> findOthersSolicitudesPending(@PathVariable Long id){
        List<SolicitudIntercambio> solicitudes=jornadaService.findOthersSolicitudesPending(id);
        return solicitudes;
    }

    @RequestMapping(value="/jornada/find_own_solicitudes/{id}")
    public List<SolicitudIntercambio> findOwnSolicitudes(@PathVariable Long id){
        List<SolicitudIntercambio> solicitudes=jornadaService.findOwnSolicitudes(id);
        return solicitudes;
    }

    @RequestMapping(value="/jornada/checkCambioJornada/{id}/{fecha}/{fechaDescanso}")
    public List<String> chackCambioJornada(@PathVariable Long id,@PathVariable String fecha, @PathVariable String fechaDescanso){
        List<String> jornadas=jornadaService.chackCambioJornada(id,  fecha, fechaDescanso);
        return jornadas;
    }

    //Put

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value="jornada/aceptar_solicitud")
    public void aceptarSolicitud(@RequestBody Long id){
        jornadaService.aceptarSolicitud(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value="jornada/rechazar_solicitud")
    public void rechazarSolicitud(@RequestBody Long id){
        jornadaService.rechazarSolicitud(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value="jornada/reasignar")
    public void reasignar(@RequestBody SolicitudIntercambio solicitud){
        jornadaService.reasignar(solicitud);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value="jornada/realizarCambio")
    public void realizarCambio(@RequestBody Long idSolicitud, @RequestBody Long idNuevoEmpleado){
        jornadaService.realizarCambio(idSolicitud, idNuevoEmpleado);
    }

    //Post

    @PostMapping("/jornada/consultar")
    public List<Tarea> getJornadaByDateAndEmpleoyee(@RequestBody Date date, @RequestBody Long idEmployee){
        return jornadaService.getJornadaByDateAndEmpleoyee(idEmployee, date);
    }

    @PostMapping(value="/jornada/solicitar_vacaciones")
    public void setSolicitud(@RequestBody SolicitudSimple solicitud){
        jornadaService.setSolicitud(solicitud);
    }

    @PostMapping(value="/jornada/solicitar_intercambio")
    public void addSolicitudIntercambio(@RequestBody SolicitudIntercambio solicitud){
        jornadaService.addSolicitudIntercambio(solicitud);
    }

}
