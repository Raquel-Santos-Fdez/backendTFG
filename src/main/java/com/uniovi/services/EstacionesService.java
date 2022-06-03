package com.uniovi.services;

import com.uniovi.entities.*;
import com.uniovi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EstacionesService {


    @Autowired
    public StopsRepository stopsRepository;

    @Autowired
    public StopTimeRepository stopTimeRepository;

    @Autowired
    public RouteRepository routeRepository;

    @Autowired
    public TripRepository tripRepository;


    public void addStop(Stop stop) {
        stopsRepository.save(stop);
    }

    public List<Stop> getStops() {
        List<Stop> stops=new ArrayList<>();
        stopsRepository.findAll().forEach(stops::add);
        return stops;
    }

    public Stop getStopById(String id) {
        Optional<Stop> stop= stopsRepository.findById(id);
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

    public void addRoute(Route route) {
        routeRepository.save(route);
    }

    public List<Route> getRoutes() {
        List<Route> routes=new ArrayList<>();
        routeRepository.findAll().forEach(routes::add);
        return routes;
    }

    public Route getRouteById(String id) {
        Optional<Route> route= routeRepository.findById(id);
        if(route.isPresent())
            return route.get();
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
}
