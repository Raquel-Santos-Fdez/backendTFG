package com.uniovi.services;

import com.uniovi.entities.Route_stop;
import com.uniovi.entities.Ruta;
import com.uniovi.repositories.RouteStopsRepository;
import com.uniovi.repositories.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RutaService {


    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private RouteStopsRepository routeStopsRepository;

    public List<Ruta> getRutas() {
        List<Ruta> rutas = new ArrayList<>();
        rutaRepository.findAll().forEach(rutas::add);
        return rutas;
    }

    public List<Route_stop> findRutaByEstaciones(String origenId, String destinoId) {
        return rutaRepository.findRutaByEstaciones(origenId, destinoId);
    }

    public void addRuta(Ruta ruta) {
        if (ruta != null)
            rutaRepository.save(ruta);
    }


    public Ruta getRutaById(String id) {
        Optional<Ruta> ruta = rutaRepository.findById(id);
        if (ruta.isPresent())
            return ruta.get();
        return null;
    }

    public void eliminarRuta(Ruta ruta){
        rutaRepository.delete(ruta);
    }

    public void addRutaStop(Route_stop route_stop){
        routeStopsRepository.save(route_stop);
    }

    public void eliminarRutaStops() {
        routeStopsRepository.deleteAll();
    }
}
