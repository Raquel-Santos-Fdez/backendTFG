package com.uniovi.services;

import com.uniovi.entities.Tarea_stops;
import com.uniovi.repositories.TareaStopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaStopsService {

    @Autowired
    private TareaStopsRepository tareaStopsRepository;

    public void addNuevaTareaStop(Tarea_stops tarea_stops) {
        tareaStopsRepository.save(tarea_stops);
    }
}
