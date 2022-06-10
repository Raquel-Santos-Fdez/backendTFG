package com.uniovi.repositories;

import com.uniovi.entities.Jornada;
import com.uniovi.entities.Tarea;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface JornadaRepository extends CrudRepository<Jornada, Long> {

    @Query("select t from Tarea t join t.jornada j join j.employee e where e.id=?1 and j.date=?2" +
            " order by t.horaSalida")
    List<Tarea> findByDateAndEmployee(Long id, Date date);
}
