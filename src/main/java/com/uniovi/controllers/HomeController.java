package com.uniovi.controllers;

import com.uniovi.entities.Route;
import com.uniovi.entities.Route_stop;
import com.uniovi.entities.Stop;
import com.uniovi.entities.Stop_time;
import com.uniovi.services.EstacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private EstacionesService estacionesService;

    @RequestMapping("/")
    @CrossOrigin(origins="*", maxAge = 3600)
    public String inicio(){
        return "inicio";
    }

    @RequestMapping(value = "/stops")
    public List<Stop> getStops() {
        return estacionesService.getStops();
    }

    @RequestMapping(value = "/stop_times")
    public List<Stop_time> getStopTimes() {
        return estacionesService.getStopTimes();
    }

    @RequestMapping(value = "/routes/{id}")
    public Route getRouteById(@PathVariable String id) {
        return estacionesService.getRouteById(id);
    }

//    @RequestMapping(value = "/stop_times/{id}/{nombreRuta}")
//    public List<Stop_time> findStopTimeByRouteId(@PathVariable String id,@PathVariable String nombreRuta) {
//        return estacionesService.findStopTimeByRouteId(id, nombreRuta);
//    }

    @RequestMapping(value = "/route_by_stop/{stopId}")
    public List<Route> getRoutesByStop(@PathVariable String stopId) {
        return estacionesService.getRoutesByStop(stopId);
    }

    @RequestMapping(value = "/stop_times/{routeId}/{stopId}")
    public List<Stop_time> findStopTimeByRouteStop(@PathVariable String routeId,@PathVariable String stopId) {
        return estacionesService.findStopTimeByRouteStop(routeId, stopId);
    }

}
