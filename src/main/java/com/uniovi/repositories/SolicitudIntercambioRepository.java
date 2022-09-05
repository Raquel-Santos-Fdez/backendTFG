package com.uniovi.repositories;

import com.uniovi.entities.Solicitud;
import com.uniovi.entities.SolicitudIntercambio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface SolicitudIntercambioRepository extends CrudRepository<SolicitudIntercambio, Long> {

    @Query("select s from SolicitudIntercambio s  where s.estado='PENDIENTE' or s.estado='REASIGNADA'")
    List<SolicitudIntercambio> findAllPending();


    @Query("select s from SolicitudIntercambio s where s.estado='PENDIENTE' and s.empleado.id<>?1")
    List<SolicitudIntercambio> findOthersSolicitudesPending(Long id);

    @Query("select s from Solicitud s where s.empleado.id=?1")
    List<Solicitud> findOwnSolicitudes(Long id);

}
