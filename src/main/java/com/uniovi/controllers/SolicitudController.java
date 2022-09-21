package com.uniovi.controllers;

import com.uniovi.entities.Solicitud;
import com.uniovi.entities.SolicitudIntercambio;
import com.uniovi.entities.SolicitudSimple;
import com.uniovi.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    @RequestMapping(value="/solicitudes/findSolicitudByFechaEmpleado/{date}/{id}")
    public List<Solicitud> findSolicitudByFechaEmpleado(@PathVariable String date, @PathVariable Long id){
        return solicitudService.findSolicitudByFechaEmpleado(date, id);
    }

//    @GetMapping(value="/solicitudes/gestionarSolicitudAceptada/{solicitud}")
//    public Solicitud gestionarSolicitudAceptada(@PathVariable Solicitud solicitud ){
//         return solicitudService.gestionarSolicitudAceptada(solicitud);
//    }

    @RequestMapping(value="/solicitudes/find-own-solicitudes/{id}")
    public List<Solicitud> findOwnSolicitudes(@PathVariable Long id){
        return solicitudService.findOwnSolicitudes(id);
    }

    @RequestMapping(value="/solicitudes/find-others-solicitudes/{id}")
    public List<SolicitudIntercambio> findOthersSolicitudesPending(@PathVariable Long id ){
        return solicitudService.findOthersSolicitudesPending(id);
    }

    //Put

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value="solicitudes/aceptar-solicitud")
    public void aceptarSolicitud(@RequestBody Solicitud solicitud){
        solicitudService.aceptarSolicitud(solicitud);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value="solicitudes/rechazar-solicitud")
    public void rechazarSolicitud(@RequestBody Long id){
        solicitudService.rechazarSolicitud(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value="/solicitudes/gestionarSolicitudAceptada")
    public void gestionarSolicitudAceptada(@RequestBody Solicitud solicitud ){
         solicitudService.gestionarSolicitudAceptada(solicitud);
    }


    @PostMapping(value="/jornada/solicitar-vacaciones")
    public void setSolicitud(@RequestBody SolicitudSimple solicitud){
        solicitudService.setSolicitud(solicitud);
    }
}
