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

    @RequestMapping(value = "/solicitudes/find-solicitudes-vacaciones-pendientes/{id}")
    public List<SolicitudVacaciones> findSolicitudesVacacionesPendientes(@PathVariable Long id) {
        return solicitudService.findSolicitudesVacacionesPendientes(id);
    }

    @RequestMapping(value="/solicitudes/ver-solicitudes-pendientes")
    public List<Solicitud> getAllSolicitudesPendientes(){
        return solicitudService.getAllSolicitudesPendientes();
    }

    @RequestMapping(value = "/solicitudes/findSolicitudByDateEmployee/{fecha}/{idEmpleado}")
    public boolean findSolicitudByDateEmployee(@PathVariable String fecha, @PathVariable Long idEmpleado){
        return solicitudService.findSolicitudByDateEmployee(fecha, idEmpleado);
    }

    //Put

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "/solicitudes/find-others-solicitudes")
    public List<SolicitudIntercambio> findOthersSolicitudesPendientes(@RequestBody Empleado empleado) {
        return solicitudService.findOthersSolicitudesPendientes(empleado);
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

    //Post

    @PostMapping(value = "/solicitudes/enviar-solicitud")
    public void setSolicitud(@RequestBody SolicitudSimple solicitud) {
        solicitudService.setSolicitud(solicitud);
    }

    @PostMapping(value = "/solicitudes/solicitar-vacaciones")
    public void solicitarVacaciones(@RequestBody SolicitudVacaciones solicitud) {
        solicitudService.solicitarVacaciones(solicitud);
    }

    @PostMapping(value="/solicitudes/solicitar-intercambio")
    public void addSolicitudIntercambio(@RequestBody Solicitud solicitud){
        solicitudService.addSolicitudIntercambio(solicitud);
    }




}
