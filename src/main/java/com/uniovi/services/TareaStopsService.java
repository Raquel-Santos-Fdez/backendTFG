package com.uniovi.services;

import com.uniovi.entities.Stop;
import com.uniovi.entities.Tarea;
import com.uniovi.entities.Tarea_stops;
import com.uniovi.repositories.TareaStopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaStopsService {

    @Autowired
    private TareaStopsRepository tareaStopsRepository;


    public Tarea_stops addTareaStop(Tarea tarea, Stop origen, String inicio) {
        Tarea_stops tarea_stop;
        if(inicio.equals("INICIO"))
            tarea_stop=new Tarea_stops(Tarea_stops.Situacion.INICIO,origen, tarea );
        else
            tarea_stop=new Tarea_stops(Tarea_stops.Situacion.FINAL,origen, tarea );
        return tareaStopsRepository.save(tarea_stop);
    }
}
