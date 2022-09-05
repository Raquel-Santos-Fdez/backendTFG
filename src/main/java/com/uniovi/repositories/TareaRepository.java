package com.uniovi.repositories;

import com.uniovi.entities.Estacion;
import com.uniovi.entities.Tarea;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TareaRepository extends CrudRepository<Tarea, Long> {

    @Query("select s from tarea_stops ts join ts.estacion s where ts.id=?1")
    List<Estacion> findStopByTarea(Long id);
}
