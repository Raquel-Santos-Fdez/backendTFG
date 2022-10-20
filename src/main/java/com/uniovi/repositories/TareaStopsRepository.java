package com.uniovi.repositories;

import com.uniovi.entities.Tarea_stops;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TareaStopsRepository extends CrudRepository<Tarea_stops, Long> {

}
