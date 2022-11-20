package com.uniovi.services;

import com.uniovi.entities.Ruta_estacion;
import com.uniovi.entities.Ruta;
import com.uniovi.repositories.RutaEstacionRepository;
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
    private RutaEstacionRepository rutaEstacionRepository;

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
    public List<Ruta_estacion> findRutaByEstaciones(String origenId, String destinoId) {
        List<Ruta_estacion> rutas_conjuntas=new ArrayList<>();

        List<Ruta_estacion> rutas_estaciones_origen=rutaRepository.findRutaByEstaciones(origenId, destinoId);

        List<Ruta_estacion> rutas_estaciones_destino=rutaRepository.findRutaByEstaciones(destinoId, origenId);

        for(Ruta_estacion rs_origen: rutas_estaciones_origen) {
            for(Ruta_estacion rs_destino:rutas_estaciones_destino) {
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
     * Añade una ruta_estacion
     * @param rutaestacion ruta_estacion a añadir
     */
    public void addRutaEstacion(Ruta_estacion rutaestacion){
        rutaEstacionRepository.save(rutaestacion);
    }

    /**
     * Elimina todas las ruta_estacion
     */
    public void eliminarRutaEstaciones() {
        rutaEstacionRepository.deleteAll();
    }
}
