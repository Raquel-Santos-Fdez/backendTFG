package com.uniovi.repositories;

import com.uniovi.entities.Empleado;
import com.uniovi.entities.Jornada;
import com.uniovi.entities.Tarea;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface JornadaRepository extends CrudRepository<Jornada, Long> {

    @Query("select t from Jornada j join j.tareas t join j.empleado e where e.id=?1 and j.date=?2" +
            " order by t.horaSalida")
    List<Tarea> findTareaByDateAndEmpleado(Long id, Date date);

    @Modifying
    @Query("update Jornada j set j.empleado=?1 where j.id=?2")
    void cambiarJornadaEmpleado(Empleado empleado, Long idJornada);

    @Query("select j from Jornada j join j.empleado e where e.id=?1")
    List<Jornada> findJornadaByEmpleado(Long id);

    @Query("select j from Jornada j where j.date=?1")
    List<Jornada> findJornadaByDate(Date date);

    @Query("select j from Jornada j where j.empleado.id=?2 and j.date=?1 ")
    List<Jornada> findJornadaByDateEmpleado(Date date, Long id);


}
