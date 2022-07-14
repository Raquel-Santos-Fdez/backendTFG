package com.uniovi.services;

import com.uniovi.entities.Route;
import com.uniovi.entities.Route_stop;
import com.uniovi.repositories.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RutaService {


    @Autowired
    public RutaRepository rutaRepository;

    public List<Route> getRoutes() {
        List<Route> routes=new ArrayList<>();
        rutaRepository.findAll().forEach(routes::add);
        return routes;
    }

    public List<Route_stop> findRutaByEstaciones(String origenId, String destinoId) {
        return rutaRepository.findRutaByEstaciones(origenId, destinoId);
    }
}
