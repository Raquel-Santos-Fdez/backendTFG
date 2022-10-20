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

@Service
public class EstacionService {


    @Autowired
    private EstacionRepository estacionRepository;

    @Autowired
    private StopTimeRepository stopTimeRepository;

    @Autowired
    private TripRepository tripRepository;


    public void addEstacion(Estacion estacion) {
        estacionRepository.save(estacion);
    }

    public List<Estacion> getEstaciones() {
        List<Estacion> estacions = new ArrayList<>();
        estacionRepository.findAll().forEach(estacions::add);
        return estacions;
    }

    public Estacion getEstacionById(String id) {
        Optional<Estacion> stop = estacionRepository.findById(id);
        if (stop.isPresent())
            return stop.get();
        return null;
    }

    public void addStopTimes(Stop_time st) {
        stopTimeRepository.save(st);
    }

    public List<Stop_time> getStopTimes() {
        List<Stop_time> stopTimes = new ArrayList<>();
        stopTimeRepository.findAll().forEach(stopTimes::add);
        return stopTimes;
    }


    public void addTrip(Trip trip) {
        tripRepository.save(trip);
    }

    public Trip getTripById(String id) {
        Optional<Trip> trip = tripRepository.findById(id);
        if (trip.isPresent())
            return trip.get();
        return null;
    }

    public List<Stop_time> findStopTimeByRouteStop(String routeId, String stopId) {
        return stopTimeRepository.findStopTimeByRouteStop(routeId, stopId);
    }


    public void deleteEstacion(String id) {
        if (getEstacionById(id) != null)
            estacionRepository.deleteById(id);
    }
}
