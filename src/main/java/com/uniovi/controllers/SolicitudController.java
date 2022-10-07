package com.uniovi.controllers;

import com.uniovi.entities.*;
import com.uniovi.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @RequestMapping(value = "/solicitudes/findSolicitudByFechaEmpleado/{date}/{id}")
    public List<Solicitud> findSolicitudByFechaEmpleado(@PathVariable String date, @PathVariable Long id) {
        return solicitudService.findSolicitudByFechaEmpleado(date, id);
    }

    @RequestMapping(value = "/solicitudes/find-own-solicitudes/{id}")
    public List<Solicitud> findOwnSolicitudes(@PathVariable Long id) {
        return solicitudService.findOwnSolicitudes(id);
    }



    @RequestMapping(value = "/solicitudes/find-solicitudes-vacaciones/{id}")
    public List<SolicitudVacaciones> findSolicitudesVacaciones(@PathVariable Long id) {
        return solicitudService.findSolicitudesVacaciones(id);
    }

    @RequestMapping(value="/solicitudes/ver-solicitudes-vacaciones")
    public List<Solicitud> getAllSolicitudesPendientes(){
        return solicitudService.getAllSolicitudesPendientes();
    }

    //Put

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "/solicitudes/find-others-solicitudes")
    public List<SolicitudIntercambio> findOthersSolicitudesPending(@RequestBody Empleado empleado) {
        return solicitudService.findOthersSolicitudesPending(empleado);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "solicitudes/aceptar-solicitud")
    public void aceptarSolicitud(@RequestBody Solicitud solicitud) {
        solicitudService.aceptarSolicitud(solicitud);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "solicitudes/rechazar-solicitud")
    public void rechazarSolicitud(@RequestBody Long id) {
        solicitudService.rechazarSolicitud(id);
    }

    @PostMapping(value = "/jornada/enviar-solicitud")
    public void setSolicitud(@RequestBody SolicitudSimple solicitud) {
        solicitudService.setSolicitud(solicitud);
    }

    @PostMapping(value = "/jornada/solicitar-vacaciones")
    public void solicitarVacaciones(@RequestBody SolicitudVacaciones solicitud) {
        solicitudService.solicitarVacaciones(solicitud);
    }



}
