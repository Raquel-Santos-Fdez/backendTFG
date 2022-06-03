package com.uniovi.controllers;

import com.uniovi.entities.Jornada;
import com.uniovi.entities.Route;
import com.uniovi.entities.Stop;
import com.uniovi.entities.Stop_time;
import com.uniovi.services.EstacionesService;
import com.uniovi.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private InfoService infoService;

    @Autowired
    private EstacionesService estacionesService;

    @RequestMapping("/")
    @CrossOrigin(origins="*", maxAge = 3600)
    public String inicio(){
        return "inicio";
    }

    @RequestMapping(value = "/horarios", method = RequestMethod.GET)
    public String getHorarios(Model model) {
        model.addAttribute("listaRutas", infoService.getRutas());
        return "horarios";
    }

    @RequestMapping(value = "/stops")
    public List<Stop> getStops() {
        return estacionesService.getStops();
    }

    @RequestMapping(value = "/stop_times")
    public List<Stop_time> getStopTimes() {
        return estacionesService.getStopTimes();
    }

    @RequestMapping(value = "/routes")
    public List<Route> getRoutes() {
        return estacionesService.getRoutes();
    }

    @RequestMapping(value = "/routes/{id}")
    public Route getRouteById(@PathVariable String id) {
        return estacionesService.getRouteById(id);
    }

    @RequestMapping(value = "/stop_times/{id}/{nombreRuta}")
    public List<Stop_time> findStopTimeByRouteId(@PathVariable String id,@PathVariable String nombreRuta) {
        return estacionesService.findStopTimeByRouteId(id, nombreRuta);
    }


}
