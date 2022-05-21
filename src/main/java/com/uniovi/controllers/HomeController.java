package com.uniovi.controllers;

import com.uniovi.entities.Estacion;
import com.uniovi.services.EstacionesService;
import com.uniovi.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private InfoService infoService;

    @Autowired
    private EstacionesService estacionesService;

    @RequestMapping("/")
    public String inicio(){
        return "inicio";
    }

    @RequestMapping(value = "/horarios", method = RequestMethod.GET)
    public String getHorarios(Model model) {
        model.addAttribute("listaRutas", infoService.getRutas());
        return "horarios";
    }

    @RequestMapping(value = "/estaciones")
    public List<Estacion> getEstaciones() {
        return estacionesService.getEstaciones();
    }

}
