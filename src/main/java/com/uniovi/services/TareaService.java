package com.uniovi.services;

import com.uniovi.entities.Tarea;
import com.uniovi.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicios encargado de la gestión de las tareas
 *
 * @author UO266047
 */
@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    /**
     * Busca una tarea por id
     * @param id id de la tarea a buscar
     * @return Tarea encontrada o null
     */
    public Tarea findTareaById(Long id) {
        Tarea tarea = null;
        if (id != null) {
            if (tareaRepository.findById(id).isPresent())
                return tareaRepository.findById(id).get();
        }
        return tarea;
    }

    /**
     * Elimina una tarea
     * @param tarea tarea a eliminar
     */
    public void eliminarTarea(Tarea tarea) {
        if (tarea != null)
            tareaRepository.delete(tarea);
    }

    /**
     * Busca todas las tareas
     * @return Lista de todas las tareas
     */
    public List<Tarea> getTareas() {
        List<Tarea> tareas = new ArrayList<>();
        tareaRepository.findAll().forEach(tareas::add);
        return tareas;
    }

}
