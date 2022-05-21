package com.uniovi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JornadaController {

    @RequestMapping("/jornada/portalSolicitudes")
    public String portalSolicitudes(){
        return "jornada/portalSolicitudes";
    }

    @RequestMapping("/jornada/solicitarVacaciones")
    public String solicitarVacaciones(){
        return "jornada/solicitarVacaciones";
    }

    @RequestMapping("/jornada/verJornada")
    public String verJornada(){
        return "jornada/verJornada";
    }
}
