package com.uniovi.services;

import com.uniovi.entities.Tarea_estacion;
import com.uniovi.repositories.TareaEstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicios encargado de la gestión de las tarea_estacion
 *
 * @author UO266047
 */
@Service
public class TareaEstacionService {

    @Autowired
    private TareaEstacionRepository tareaEstacionRepository;

    /**
     * Añade una nueva tarea_estacion
     * @param tarea_estacion tarea_estacion a añadir
     */
    public void addNuevaTareaEstacion(Tarea_estacion tarea_estacion) {
        if (tarea_estacion != null)
            tareaEstacionRepository.save(tarea_estacion);
    }

    /**
     * Busca todas las tarea_estacion
     * @return lista con todas las tarea_estacion
     */
    public List<Tarea_estacion> getAll() {
        List<Tarea_estacion> ts = new ArrayList<>();
        tareaEstacionRepository.findAll().forEach(ts::add);
        return ts;

    }

    /**
     * Elimina todas las tarea_estacion
     */
    public void eliminarTodos(){
        tareaEstacionRepository.deleteAll();
    }

}
