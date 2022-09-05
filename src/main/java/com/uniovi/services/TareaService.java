package com.uniovi.services;

import com.uniovi.entities.Tarea;
import com.uniovi.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    public Tarea addTarea(Tarea t) {
        return tareaRepository.save(t);
    }


    public Tarea findTareaById(Long id) {
        return tareaRepository.findById(id).get();
    }
}
