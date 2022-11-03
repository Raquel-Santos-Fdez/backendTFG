package com.uniovi.services;

import com.uniovi.entities.Estacion;
import com.uniovi.entities.Stop_time;
import com.uniovi.entities.Trip;
import com.uniovi.repositories.EstacionRepository;
import com.uniovi.repositories.StopTimeRepository;
import com.uniovi.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicios encargado de la gestión de las estaciones
 *
 * @author UO266047
 */
@Service
public class EstacionService {


    @Autowired
    private EstacionRepository estacionRepository;

    @Autowired
    private StopTimeRepository stopTimeRepository;

    @Autowired
    private TripRepository tripRepository;

    /**
     * Añade una nueva estación
     * @param estacion estacion a añadir
     */
    public void addEstacion(Estacion estacion) {
        estacionRepository.save(estacion);
    }

    /**
     * Obtiene todas las estaciones
     * @return lista que contiene todas las estaciones
     */
    public List<Estacion> getEstaciones() {
        List<Estacion> estacions = new ArrayList<>();
        estacionRepository.findAll().forEach(estacions::add);
        return estacions;
    }

    /**
     * Busca una estacion por id
     * @param id id de la estacion a buscar
     * @return estacion encontrada o null
     */
    public Estacion getEstacionById(String id) {
        Optional<Estacion> stop = estacionRepository.findById(id);
        if (stop.isPresent())
            return stop.get();
        return null;
    }

    /**
     * Añade una nueva stop_time
     * @param st nueva stop_time a añadir
     */
    public void addStopTimes(Stop_time st) {
        stopTimeRepository.save(st);
    }

    /**
     * Obtiene todas las stop_times
     * @return Lista con todas las stop_times
     */
    public List<Stop_time> getStopTimes() {
        List<Stop_time> stopTimes = new ArrayList<>();
        stopTimeRepository.findAll().forEach(stopTimes::add);
        return stopTimes;
    }

    /**
     * Añade un nuevo "trip"
     * @param trip nuevo trip a añadir
     */
    public void addTrip(Trip trip) {
        tripRepository.save(trip);
    }

    /**
     * Busca un trip por id
     * @param id id del trip a encontrar
     * @return trip encontrado o null
     */
    public Trip getTripById(String id) {
        Optional<Trip> trip = tripRepository.findById(id);
        if (trip.isPresent())
            return trip.get();
        return null;
    }

    /**
     * Obtiene las stop_times de una ruta que incluye una estacion
     * @param routeId id de la ruta
     * @param stopId id de la estacion
     * @return lista de las stop_times
     */
    public List<Stop_time> findStopTimeByRouteStop(String routeId, String stopId) {
        return stopTimeRepository.findStopTimeByRouteStop(routeId, stopId);
    }

    /**
     * Elimina una estacion
     * @param id id de la estacion a eliminar
     */
    public void deleteEstacion(String id) {
        if (getEstacionById(id) != null)
            estacionRepository.deleteById(id);
    }
}
