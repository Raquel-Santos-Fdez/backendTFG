package com.uniovi.repositories;

import com.uniovi.entities.Solicitud;
import com.uniovi.entities.SolicitudSimple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolicitudSimpleRepository extends CrudRepository<SolicitudSimple, Long> {

    @Query("select s from SolicitudSimple s  where s.estado='PENDIENTE'")
    List<SolicitudSimple> findAllPending();

    @Query("select s from SolicitudSimple s where s.empleado.id=?1")
    List<SolicitudSimple> findOwnSolicitudesSimples(Long id);

    @Query("select s from SolicitudSimple s where s.fecha=?1 and s.empleado.id=?2")
    List<SolicitudSimple> findSolicitudSimpleByFechaEmpleado(String date, Long id);
}
