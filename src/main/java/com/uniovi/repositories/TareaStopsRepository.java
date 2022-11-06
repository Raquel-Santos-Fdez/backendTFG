package com.uniovi.repositories;

import com.uniovi.entities.Tarea_stops;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TareaStopsRepository extends CrudRepository<Tarea_stops, Long> {

}
