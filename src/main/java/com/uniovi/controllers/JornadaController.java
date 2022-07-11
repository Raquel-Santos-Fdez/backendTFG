package com.uniovi.controllers;

import com.uniovi.entities.*;
import com.uniovi.services.JornadaService;
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

   //Get

    @RequestMapping(value="/jornada/consultar/{date}/{id}")
    public List<Tarea> getTareasByDateAndEmpleoyee(@PathVariable Date date, @PathVariable String id){
        return jornadaService.getJornadaByDateAndEmpleoyee(Long.parseLong(id), date);
    }

    @RequestMapping(value="/jornada/ver_solicitudes_vacaciones")
    public List<Solicitud> getAllSolicitudesPendientes(){
        return jornadaService.getAllSolicitudesPendientes();
    }

    @RequestMapping(value="/tarea_stop/{id}")
    public Stop findStopByTarea(@PathVariable Long id){
        return jornadaService.findStopByTarea(id);
    }

    @RequestMapping(value="/jornada/find_others_solicitudes/{id}")
    public List<SolicitudIntercambio> findOthersSolicitudesPending(@PathVariable Long id){
        return jornadaService.findOthersSolicitudesPending(id);
    }

    @RequestMapping(value="/jornada/find_own_solicitudes/{id}")
    public List<SolicitudIntercambio> findOwnSolicitudes(@PathVariable Long id){
        return jornadaService.findOwnSolicitudes(id);
    }

    @RequestMapping(value="/jornada/checkCambioJornada/{id}/{fecha}/{fechaDescanso}")
    public List<String> chackCambioJornada(@PathVariable Long id,@PathVariable String fecha, @PathVariable String fechaDescanso){
        return jornadaService.chackCambioJornada(id,  fecha, fechaDescanso);
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

    @PostMapping(value="/tarea/addTarea")
    public void addTarea(@RequestBody Tarea tarea){
        jornadaService.addTarea(tarea);
    }

    @PostMapping(value="/jornada/addJornada")
    public void addJornada(@RequestBody Jornada jornada){
        jornadaService.addJornada(jornada);
    }

    @PostMapping(value="/tarea_stop/addTareaStop")
    public void addTareaStop(@RequestBody Tarea tarea, @RequestBody Stop origen, @RequestBody String inicio){
        System.out.println("esto es una prueba");
        tareaStopsService.addTareaStop(tarea, origen, inicio);
    }

}
