package com.uniovi.repositories;

import com.uniovi.entities.Solicitud;
import com.uniovi.entities.SolicitudIntercambio;
import com.uniovi.entities.SolicitudSimple;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolicitudIntercambioRepository extends CrudRepository<SolicitudIntercambio, Long> {

    @Query("select s from SolicitudIntercambio s  where s.estado='PENDIENTE' or s.estado='REASIGNADA'")
    List<SolicitudIntercambio> findAllPending();


    @Query("select s from SolicitudIntercambio s where s.estado='PENDIENTE' and s.employee.id<>?1")
    List<SolicitudIntercambio> findOthersSolicitudesPending(Long id);

    @Query("select s from SolicitudIntercambio s where s.employee.id=?1")
    List<SolicitudIntercambio> findOwnSolicitudes(Long id);

}
