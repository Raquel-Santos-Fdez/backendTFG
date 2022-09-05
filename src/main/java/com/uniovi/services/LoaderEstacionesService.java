package com.uniovi.services;

import com.uniovi.entities.*;
import com.uniovi.util.LectorCSV;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

//@Service
public class LoaderEstacionesService {

    @Autowired
    private EstacionService estacionService;

    @Autowired
    private RutaService rutaService;

    //Leemos de fichero
    private LectorCSV lector=new LectorCSV();

    public List<String> stopsIds=new ArrayList<>();

    private List<Estacion> getStopsFromFile() throws  FileNotFoundException{
        List<String> lineas=lector.readLines("listadoEstaciones.csv");
        String[] stop;
        List<Estacion> estacions =new ArrayList<>();
        for(int i=1; i<lineas.size();i++){
            stop=lineas.get(i).split(";");
            stopsIds.add(stop[0]);
            estacions.add(new Estacion(stop[0],stop[1], Double.parseDouble(stop[2]), Double.parseDouble(stop[3])));
        }
        return estacions;
    }

    private List<Stop_time> getStopTimes() throws  FileNotFoundException{
        List<String> lineas=lector.readLines("stop_times.csv");
        String[] stop_time;
        List<Stop_time> stop_times=new ArrayList<>();
        for(int i=1; i<lineas.size();i++){
            stop_time=lineas.get(i).split(";");
            //hacerlo por consulta o en una clase apropiada
            Estacion estacion = estacionService.getStopById(stop_time[3]);
            Trip trip= estacionService.getTripById(stop_time[0]);
            if(estacion !=null && trip!=null)
                stop_times.add(new Stop_time(trip, estacion,
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
            Ruta ruta = estacionService.getRouteById(trip[1]);
            if(ruta !=null && trip[0].contains("L"))
                trips.add(new Trip(trip[0], ruta));
        }
        return trips;
    }

    private List<Ruta> getRutas() throws  FileNotFoundException{
        List<String> lineas=lector.readLines("rutas.csv");
        String[] ruta;
        List<Ruta> rutas =new ArrayList<>();
        for(int i=1; i<lineas.size();i++){
            ruta=lineas.get(i).split(";");
            rutas.add(new Ruta(ruta[0], ruta[1], ruta[2]));
        }
        return rutas;
    }

    private void loadStopToRoute(){

        List<Estacion> estaciones= estacionService.getStops();
        List<Ruta> lineas=rutaService.getRutas();

//        lineas.get
    }

    @PostConstruct
    public void init() {

        try {
            for(Estacion estacion :getStopsFromFile())
                estacionService.addStop(estacion);

            for(Ruta ruta : getRutas())
                estacionService.addRuta(ruta);

            for(Trip trip:getTrips())
                estacionService.addTrip(trip);

            for(Stop_time st:getStopTimes())
                estacionService.addStopTimes(st);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
