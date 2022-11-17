package com.uniovi.util;

import com.uniovi.entities.*;
import com.uniovi.services.EmpleadoService;
import com.uniovi.services.EstacionService;
import com.uniovi.services.RutaService;
import com.uniovi.services.TrenService;
import com.uniovi.util.LectorCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

//@Service
public class LoaderService {

    @Autowired
    private EstacionService estacionService;

    @Autowired
    private RutaService rutaService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private TrenService trenService;

    //Leemos de fichero
    private final LectorCSV lector = new LectorCSV();

    private List<String> stopsIds = new ArrayList<>();

    private List<Estacion> getEstacionesFromFile() throws FileNotFoundException {
        List<String> lineas = lector.readLines("listadoEstaciones.csv");
        String[] stop;
        List<Estacion> estacions = new ArrayList<>();
        for (int i = 1; i < lineas.size(); i++) {
            stop = lineas.get(i).split(";");
            stopsIds.add(stop[0]);
            estacions.add(new Estacion(stop[0], stop[1], Double.parseDouble(stop[2]), Double.parseDouble(stop[3])));
        }
        return estacions;
    }

    private List<Stop_time> getStopTimes() throws FileNotFoundException {
        List<String> lineas = lector.readLines("stop_times.csv");
        String[] stop_time;
        List<Stop_time> stop_times = new ArrayList<>();
        for (int i = 1; i < lineas.size(); i++) {
            stop_time = lineas.get(i).split(";");
            //hacerlo por consulta o en una clase apropiada
            Estacion estacion = estacionService.getEstacionById(stop_time[3]);
            Trip trip = estacionService.getTripById(stop_time[0]);
            if (estacion != null && trip != null)
                stop_times.add(new Stop_time(trip, estacion,
                        stop_time[1], stop_time[2], stop_time[4]));
        }
        return stop_times;
    }

    private List<Trip> getTrips() throws FileNotFoundException {
        List<String> lineas = lector.readLines("trips.csv");
        String[] trip;
        List<Trip> trips = new ArrayList<>();
        for (int i = 1; i < lineas.size(); i++) {
            trip = lineas.get(i).split(";");
            Ruta ruta = rutaService.getRutaById(trip[1]);
//            Ruta ruta = estacionService.getRutaById(trip[1]);
            if (ruta != null && trip[0].contains("L"))
                trips.add(new Trip(trip[0], ruta));
        }
        return trips;
    }

    private List<Ruta> getRutas() throws FileNotFoundException {
        List<String> lineas = lector.readLines("rutas.csv");
        String[] ruta;
        List<Ruta> rutas = new ArrayList<>();
        for (int i = 1; i < lineas.size(); i++) {
            ruta = lineas.get(i).split(";");
            rutas.add(new Ruta(ruta[0], ruta[1], ruta[2]));
        }
        return rutas;
    }

    private List<Route_stop> getRutaStops() throws FileNotFoundException {
        List<String> lineas = lector.readLines("ruta_stops.csv");
        String[] ruta_stop;
        List<Route_stop> rutas_stops = new ArrayList<>();
        for (int i = 1; i < lineas.size(); i++) {
            ruta_stop = lineas.get(i).split(";");
            Estacion estacion = estacionService.getEstacionById(ruta_stop[1]);
            Ruta ruta = rutaService.getRutaById(ruta_stop[2]);
            if (estacion != null && ruta != null)
                rutas_stops.add(new Route_stop(Integer.parseInt(ruta_stop[0]), estacion, ruta));
        }
        return rutas_stops;
    }

    private void addEmpleados(){
        Empleado admin=new Empleado("admin1", "Admin", "Prueba", "admin1@gmail.com",
                "11111111A", "Password1", Empleado.Rol.ADMIN, 100);
        empleadoService.addEmpleado(admin);

        Empleado empleado1=new Empleado("empleado1", "Empleado1", "Prueba", "empleado1@gmail.com",
                "22222222B", "Password1", Empleado.Rol.MAQUINISTA, 100);
        empleadoService.addEmpleado(empleado1);

    }

    private void addTrenes(){
        Tren tren1=new Tren();
        trenService.addTren(tren1);

        Tren tren2=new Tren();
        trenService.addTren(tren2);
    }

    @PostConstruct
    public void init() {

        try {
            for (Estacion estacion : getEstacionesFromFile())
                estacionService.addEstacion(estacion);

            for (Ruta ruta : getRutas())
                rutaService.addRuta(ruta);

            for (Trip trip : getTrips())
                estacionService.addTrip(trip);


            for (Stop_time st : getStopTimes())
                estacionService.addStopTimes(st);

            for(Route_stop rs:getRutaStops())
                rutaService.addRutaStop(rs);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        addEmpleados();
        addTrenes();
    }

}
