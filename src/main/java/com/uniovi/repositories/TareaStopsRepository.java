package com.uniovi.repositories;

import com.uniovi.entities.Tarea;
import com.uniovi.entities.Tarea_stops;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TareaStopsRepository extends CrudRepository<Tarea_stops, Long> {

    @Modifying
    @Query("update tarea_stops t set t.tarea=?2 where t.id=?1")
    void asignarTareaStop(Long idTareaStop, Tarea tarea);

}
