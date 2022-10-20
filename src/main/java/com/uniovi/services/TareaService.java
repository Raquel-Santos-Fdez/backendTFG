package com.uniovi.services;

import com.uniovi.entities.Tarea;
import com.uniovi.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public Tarea findTareaById(Long id) {
        Tarea tarea = null;
        if (id != null) {
            if (tareaRepository.findById(id).isPresent())
                return tareaRepository.findById(id).get();
        }
        return tarea;
    }

    public void eliminarTarea(Tarea tarea) {
        if (tarea != null)
            tareaRepository.delete(tarea);
    }

    public List<Tarea> getTareas() {
        List<Tarea> tareas = new ArrayList<>();
        tareaRepository.findAll().forEach(tareas::add);
        return tareas;
    }
}
