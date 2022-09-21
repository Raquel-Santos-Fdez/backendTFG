package com.uniovi.repositories;

import com.uniovi.entities.SolicitudSimple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolicitudSimpleRepository extends CrudRepository<SolicitudSimple, Long> {

    @Query("select s from SolicitudSimple s  where s.estado='PENDIENTE'")
    List<SolicitudSimple> findAllPending();

    @Query("select s from SolicitudSimple s where s.empleado.id=?1")
    List<SolicitudSimple> findOwnSolicitudesSimples(Long id);
}
