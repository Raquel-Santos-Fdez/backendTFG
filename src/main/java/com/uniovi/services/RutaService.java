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

/**
 * Servicios encargado de la gestión de las rutas
 *
 * @author UO266047
 */
@Service
public class RutaService {


    @Autowired
    private RutaRepository rutaRepository;

    @Autowired
    private RouteStopsRepository routeStopsRepository;

    /**
     * Busca todas las rutas
     * @return lista de las rutas
     */
    public List<Ruta> getRutas() {
        List<Ruta> rutas = new ArrayList<>();
        rutaRepository.findAll().forEach(rutas::add);
        return rutas;
    }

    /**
     * Busca las rutas que contienen dos estaciones
     * @param origenId id de la estacion de origen
     * @param destinoId id de la estacion de destino
     * @return lista de las rutas que cumplen el criterio
     */
    public List<Route_stop> findRutaByEstaciones(String origenId, String destinoId) {
        List<Route_stop> rutas_conjuntas=new ArrayList<>();

        List<Route_stop> rutas_stops_origen=rutaRepository.findRutaByEstaciones(origenId, destinoId);

        List<Route_stop> rutas_stops_destino=rutaRepository.findRutaByEstaciones(destinoId, origenId);

        for(Route_stop rs_origen: rutas_stops_origen) {
            for(Route_stop rs_destino:rutas_stops_destino) {
                if(rs_origen.getOrderParada()<rs_destino.getOrderParada())
                    if(rs_origen.getRuta().getRuta_id().equals(rs_destino.getRuta().getRuta_id()))
                        rutas_conjuntas.add(rs_origen);
            }
        }

        return rutas_conjuntas;
    }

    /**
     * Añade una ruta
     * @param ruta nueva ruta a añadir
     */
    public void addRuta(Ruta ruta) {
        if (ruta != null)
            rutaRepository.save(ruta);
    }

    /**
     * Busca una ruta por id
     * @param id id de la ruta a buscar
     * @return ruta encontrada o nul
     */
    public Ruta getRutaById(String id) {
        Optional<Ruta> ruta = rutaRepository.findById(id);
        if (ruta.isPresent())
            return ruta.get();
        return null;
    }

    /**
     * Elimina una ruta
     * @param ruta ruta a eliminar
     */
    public void eliminarRuta(Ruta ruta){
        rutaRepository.delete(ruta);
    }

    /**
     * Añade una route_stop
     * @param route_stop route_stop a añadir
     */
    public void addRutaStop(Route_stop route_stop){
        routeStopsRepository.save(route_stop);
    }

    /**
     * Elimina todas las route_stops
     */
    public void eliminarRutaStops() {
        routeStopsRepository.deleteAll();
    }
}
