package com.uniovi.services;

import com.uniovi.entities.*;
import com.uniovi.util.LectorCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

//@Service
public class LoaderEstacionesService {

    @Autowired
    private EstacionesService estacionesService;

    @Autowired
    private RutaService rutaService;

    //Leemos de fichero
    private LectorCSV lector=new LectorCSV();

    public List<String> stopsIds=new ArrayList<>();

    private List<Stop> getStopsFromFile() throws  FileNotFoundException{
        List<String> lineas=lector.readLines("listadoEstaciones.csv");
        String[] stop;
        List<Stop> stops=new ArrayList<>();
        for(int i=1; i<lineas.size();i++){
            stop=lineas.get(i).split(";");
            stopsIds.add(stop[0]);
            stops.add(new Stop(stop[0],stop[1], Double.parseDouble(stop[2]), Double.parseDouble(stop[3])));
        }
        return stops;
    }

    private List<Stop_time> getStopTimes() throws  FileNotFoundException{
        List<String> lineas=lector.readLines("stop_times.csv");
        String[] stop_time;
        List<Stop_time> stop_times=new ArrayList<>();
        for(int i=1; i<lineas.size();i++){
            stop_time=lineas.get(i).split(";");
            //hacerlo por consulta o en una clase apropiada
            System.out.println(i);
            Stop stop=estacionesService.getStopById(stop_time[3]);
            Trip trip=estacionesService.getTripById(stop_time[0]);
            if(stop!=null && trip!=null)
                stop_times.add(new Stop_time(trip,stop ,
                        stop_time[1], stop_time[2], stop_time[4]));
        }
        return stop_times;
    }

    private List<Trip> getTrips() throws  FileNotFoundException{
        List<String> lineas=lector.readLines("trips.csv");
        String[] trip;
        List<Trip> trips=new ArrayList<>();
        for(int i=1; i<lineas.size();i++){
            trip=lineas.get(i).split(";");
            Route route=estacionesService.getRouteById(trip[1]);
            if(route!=null && trip[0].contains("L"))
                trips.add(new Trip(trip[0], route));
        }
        return trips;
    }

    private List<Route> getRoutes() throws  FileNotFoundException{
        List<String> lineas=lector.readLines("rutas.csv");
        String[] route;
        List<Route> routes=new ArrayList<>();
        for(int i=1; i<lineas.size();i++){
            route=lineas.get(i).split(";");
            routes.add(new Route(route[0], route[1], route[2]));
        }
        return routes;
    }

    private void loadStopToRoute(){

        List<Stop> paradas=estacionesService.getStops();
        List<Route> lineas=rutaService.getRoutes();

//        lineas.get
    }

    @PostConstruct
    public void init() {

        try {
            for(Stop stop:getStopsFromFile())
                estacionesService.addStop(stop);

            for(Route route:getRoutes())
                estacionesService.addRoute(route);

            for(Trip trip:getTrips())
                estacionesService.addTrip(trip);

            for(Stop_time st:getStopTimes())
                estacionesService.addStopTimes(st);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
