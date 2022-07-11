package com.uniovi.repositories;

import com.uniovi.entities.Jornada;
import com.uniovi.entities.Tarea;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface JornadaRepository extends CrudRepository<Jornada, Long> {

    @Query("select t from Tarea t join t.jornada j join j.employee e where e.id=?1 and j.date=?2" +
            " order by t.horaSalida")
    List<Tarea> findByDateAndEmployee(Long id, Date date);

    @Query("select j.id from Jornada j where j.employee.id=?1 and j.employee.id not in " +
            "(select j2.employee.id from Jornada j2 where j2.date=?2) and j.employee.id  in " +
            "(select j3.employee.id from Jornada j3 where j3.date=?3)")
    List<String> chackCambioJornada(Long id,Date fecha, Date fechaDescanso);

    @Modifying
    @Query("update Jornada j set j.date=?2 where j.date=?1")
    void cambiarJornadaEmpleadoViejo(Date fechaAnterior, Date fechaNueva);

    @Modifying
    @Query("update Jornada j set j.date=?1 where j.date=?2 and j.employee.id=?3")
    void cambiarJornadaEmpleadoNuevo(Date fechaAnterior, Date fechaNueva, Long idNuevoEmpleado);

    @Query("select j from Jornada j join j.employee e where e.id=?1")
    List<Jornada> findJornadaByEmpleado(Long id);

    @Query("select j from Jornada j where j.date=?1")
    List<Jornada> findJornadaByDate(java.sql.Date date);

    @Query("select j from Jornada j join j.employee e where j.date=?1 and e.id=?2")
    List<Jornada> findJornadaByDateEmployee(java.sql.Date date, Long id);
}
