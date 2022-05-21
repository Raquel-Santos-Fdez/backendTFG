package com.uniovi.services;

import com.uniovi.entities.Estacion;
import com.uniovi.repositories.EstacionesRepository;
import com.uniovi.util.LectorCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoaderEstacionesService {

    @Autowired
    private EstacionesService estacionesService;

    //Leemos de fichero
    private LectorCSV lector=new LectorCSV();

    public List<Estacion> getEstacionesFromFile() throws FileNotFoundException {
        List<String> lineas=lector.readLines("listadoEstaciones.csv");
        String[] estacion;
        List<Estacion> estaciones=new ArrayList<>();
        for(int i=1; i<lineas.size();i++){
            estacion=lineas.get(i).split(";");
            estaciones.add(new Estacion(estacion[1], Double.parseDouble(estacion[2]), Double.parseDouble(estacion[3])));
        }
        return estaciones;
    }

    @PostConstruct
    public void init() {

        try {
            for(Estacion estacion:getEstacionesFromFile())
                estacionesService.addEstacion(estacion);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
