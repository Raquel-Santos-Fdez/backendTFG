package com.uniovi.repositories;

import com.uniovi.entities.Stop;
import com.uniovi.entities.Tarea;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TareaRepository extends CrudRepository<Tarea, Long> {

    @Query("select s from tarea_stops ts join ts.stop s where ts.id=?1")
    List<Stop> findStopByTarea(Long id);
}
