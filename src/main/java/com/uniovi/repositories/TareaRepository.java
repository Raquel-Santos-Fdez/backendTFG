package com.uniovi.repositories;

import com.uniovi.entities.Tarea;
import org.springframework.data.repository.CrudRepository;

public interface TareaRepository extends CrudRepository<Tarea, Long> {
}
