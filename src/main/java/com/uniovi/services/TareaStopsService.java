package com.uniovi.services;

import com.uniovi.entities.Tarea_stops;
import com.uniovi.repositories.TareaStopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicios encargado de la gestión de las tarea_stop
 *
 * @author UO266047
 */
@Service
public class TareaStopsService {

    @Autowired
    private TareaStopsRepository tareaStopsRepository;

    /**
     * Añade una nueva tarea_stop
     * @param tarea_stops tarea_stop a añadir
     */
    public void addNuevaTareaStop(Tarea_stops tarea_stops) {
        if (tarea_stops != null)
            tareaStopsRepository.save(tarea_stops);
    }

    /**
     * Busca todas las tarea_stops
     * @return lista con todas las tarea_stops
     */
    public List<Tarea_stops> getAll() {
        List<Tarea_stops> ts = new ArrayList<>();
        tareaStopsRepository.findAll().forEach(ts::add);
        return ts;

    }

    /**
     * Elimina todas las tarea_stops
     */
    public void eliminarTodos(){
        tareaStopsRepository.deleteAll();
    }

}
