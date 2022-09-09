package com.uniovi.repositories;

import com.uniovi.entities.Jornada;
import com.uniovi.entities.Tarea;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface JornadaRepository extends CrudRepository<Jornada, Long> {

//    @Query("select t from Tarea t join t.jornada j join j.empleado e where e.id=?1 and j.date=?2" +
//            " order by t.horaSalida")
    @Query("select t from Jornada j join j.tareas t join j.empleado e where e.id=?1 and j.date=?2" +
            " order by t.horaSalida")
    List<Tarea> findTareaByDateAndEmpleado(Long id, Date date);

    @Query("select j.id from Jornada j where j.empleado.id=?1 and j.empleado.id not in " +
            "(select j2.empleado.id from Jornada j2 where j2.date=?2) and j.empleado.id  in " +
            "(select j3.empleado.id from Jornada j3 where j3.date=?3)")
    List<String> chackCambioJornada(Long id,Date fecha, Date fechaDescanso);

    @Modifying
    @Query("update Jornada j set j.date=?2 where j.date=?1")
    void cambiarJornadaEmpleadoViejo(Date fechaAnterior, Date fechaNueva);

    @Modifying
    @Query("update Jornada j set j.date=?1 where j.date=?2 and j.empleado.id=?3")
    void cambiarJornadaEmpleadoNuevo(Date fechaAnterior, Date fechaNueva, Long idNuevoEmpleado);

    @Query("select j from Jornada j join j.empleado e where e.id=?1")
    List<Jornada> findJornadaByEmpleado(Long id);

    @Query("select j from Jornada j where j.date=?1")
    List<Jornada> findJornadaByDate(java.sql.Date date);

//    @Query("select j from Jornada j join j.empleado e where j.date=?1 and e.id=?2")
    @Query("select j from Jornada j where j.empleado.id=?2 and j.date=?1 ")
    List<Jornada> findJornadaByDateEmpleado(Date date, Long id);


}
