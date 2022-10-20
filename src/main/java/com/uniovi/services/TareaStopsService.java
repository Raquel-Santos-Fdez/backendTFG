package com.uniovi.services;

import com.uniovi.entities.Tarea_stops;
import com.uniovi.repositories.TareaStopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TareaStopsService {

    @Autowired
    private TareaStopsRepository tareaStopsRepository;

    public void addNuevaTareaStop(Tarea_stops tarea_stops) {
        if (tarea_stops != null)
            tareaStopsRepository.save(tarea_stops);
    }

    public List<Tarea_stops> getAll() {
        List<Tarea_stops> ts = new ArrayList<>();
        tareaStopsRepository.findAll().forEach(ts::add);
        return ts;

    }

    public void eliminarTodos(){
        tareaStopsRepository.deleteAll();
    }
}
