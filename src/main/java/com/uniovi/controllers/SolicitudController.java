package com.uniovi.controllers;

import com.uniovi.entities.Solicitud;
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
}
