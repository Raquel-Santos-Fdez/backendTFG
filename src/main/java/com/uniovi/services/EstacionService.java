package com.uniovi.services;

import com.uniovi.entities.*;
import com.uniovi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstacionService {


    @Autowired
    public EstacionRepository estacionRepository;

    @Autowired
    public StopTimeRepository stopTimeRepository;

    @Autowired
    public RutaRepository rutaRepository;

    @Autowired
    public TripRepository tripRepository;


    public void addStop(Estacion estacion) {
        estacionRepository.save(estacion);
    }

    public List<Estacion> getStops() {
        List<Estacion> estacions =new ArrayList<>();
        estacionRepository.findAll().forEach(estacions::add);
        return estacions;
    }

    public Estacion getStopById(String id) {
        Optional<Estacion> stop= estacionRepository.findById(id);
        if(stop.isPresent())
            return stop.get();
        return null;
    }

    public void addStopTimes(Stop_time st) {
        stopTimeRepository.save(st);
    }

    public List<Stop_time> getStopTimes() {
        List<Stop_time> stopTimes=new ArrayList<>();
        stopTimeRepository.findAll().forEach(stopTimes::add);
        return stopTimes;
    }

    public void addRuta(Ruta ruta) {
        rutaRepository.save(ruta);
    }



    public Ruta getRouteById(String id) {
        Optional<Ruta> ruta= rutaRepository.findById(id);
        if(ruta.isPresent())
            return ruta.get();
        return null;
    }

    public void addTrip(Trip trip) {
        tripRepository.save(trip);
    }

    public Trip getTripById(String id) {
        Optional<Trip> trip= tripRepository.findById(id);
        if(trip.isPresent())
            return trip.get();
        return null;
    }

    public List<Stop_time> findStopTimeByRouteId(String id, String nombreRuta) {
        List<Stop_time> stopTimes=new ArrayList<>();
        String nombreParcial='%'+nombreRuta+'%';
        stopTimeRepository.findStopTimeByRouteId(id, nombreParcial).forEach(stopTimes::add);
        return stopTimes;
    }

    public List<Ruta> getRoutesByStop(String id) {
        return rutaRepository.getRoutesByStop(id);
    }

    public List<Stop_time> findStopTimeByRouteStop(String routeId, String stopId) {
        return stopTimeRepository.findStopTimeByRouteStop(routeId, stopId);
    }


}
