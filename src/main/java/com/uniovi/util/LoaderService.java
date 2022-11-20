package com.uniovi.util;

import com.uniovi.entities.*;
import com.uniovi.services.EmpleadoService;
import com.uniovi.services.EstacionService;
import com.uniovi.services.RutaService;
import com.uniovi.services.TrenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.time.LocalTime;
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

    private List<Horario> getHorarios() throws FileNotFoundException {
        List<String> lineas = lector.readLines("horarios.csv");
        String[] horariosString;
        List<Horario> horarios = new ArrayList<>();
        for (int i = 1; i < lineas.size(); i++) {
            horariosString = lineas.get(i).split(";");
            //hacerlo por consulta o en una clase apropiada
            Estacion estacion = estacionService.getEstacionById(horariosString[3]);
            Trayecto trayecto = estacionService.getTrayectoById(horariosString[0]);
            horariosString = formatearHorarios(horariosString);
            if (estacion != null && trayecto != null)
                horarios.add(new Horario(trayecto, estacion,
                        LocalTime.parse(horariosString[1]), LocalTime.parse(horariosString[2])));
        }
        return horarios;
    }

    private String[] formatearHorarios(String[] horariosString) {
        String[] horariosV = horariosString;
        for (int i = 0; i < horariosString.length-1; i++)
            if (horariosString[i].length() == 7)
                horariosV[i] = ("0" + horariosString[i]);
            else if(horariosString[i].split(":")[0].equals("24"))
                horariosV[i]=("00:"+horariosString[i].split(":")[1]+":"+horariosString[i].split(":")[2]);
            else
                horariosV[i] = horariosString[i];

        return horariosV;
    }

    private List<Trayecto> getTrayectos() throws FileNotFoundException {
        List<String> lineas = lector.readLines("trayectos.csv");
        String[] trayecto;
        List<Trayecto> trayectos = new ArrayList<>();
        for (int i = 1; i < lineas.size(); i++) {
            trayecto = lineas.get(i).split(";");
            Ruta ruta = rutaService.getRutaById(trayecto[1]);
            if (ruta != null && trayecto[0].contains("L"))
                trayectos.add(new Trayecto(trayecto[0], ruta));
        }
        return trayectos;
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

    private List<Ruta_estacion> getRutaStops() throws FileNotFoundException {
        List<String> lineas = lector.readLines("ruta_stops.csv");
        String[] ruta_stop;
        List<Ruta_estacion> rutas_stops = new ArrayList<>();
        for (int i = 1; i < lineas.size(); i++) {
            ruta_stop = lineas.get(i).split(";");
            Estacion estacion = estacionService.getEstacionById(ruta_stop[1]);
            Ruta ruta = rutaService.getRutaById(ruta_stop[2]);
            if (estacion != null && ruta != null)
                rutas_stops.add(new Ruta_estacion(Integer.parseInt(ruta_stop[0]), estacion, ruta));
        }
        return rutas_stops;
    }

    private void addEmpleados() {
        Empleado admin = new Empleado("admin1", "Admin", "Prueba", "admin1@gmail.com",
                "11111111A", "Password10", Empleado.Rol.ADMIN, 100);
        empleadoService.addEmpleado(admin);

        Empleado empleado1 = new Empleado("empleado1", "Empleado1", "Prueba", "empleado1@gmail.com",
                "22222222B", "Password11", Empleado.Rol.MAQUINISTA, 100);
        empleadoService.addEmpleado(empleado1);

        Empleado empleado3 = new Empleado("empleado3", "Empleado3", "Prueba", "empleado3@gmail.com",
                "22222222C", "Password12", Empleado.Rol.MAQUINISTA, 100);
        empleadoService.addEmpleado(empleado3);

    }

    private void addTrenes() {
        Tren tren1 = new Tren();
        trenService.addTren(tren1);

        Tren tren2 = new Tren();
        trenService.addTren(tren2);
    }

    @PostConstruct
    public void init() {

        try {
            for (Estacion estacion : getEstacionesFromFile())
                estacionService.addEstacion(estacion);

            for (Ruta ruta : getRutas())
                rutaService.addRuta(ruta);

            for (Trayecto trayecto : getTrayectos())
                estacionService.addTrayecto(trayecto);


            for (Horario st : getHorarios())
                estacionService.addHorario(st);

            for (Ruta_estacion rs : getRutaStops())
                rutaService.addRutaEstacion(rs);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        addEmpleados();
        addTrenes();
    }

}
