package com.uniovi.repositories;

import com.uniovi.entities.Empleado;
import com.uniovi.entities.Solicitud;
import com.uniovi.entities.SolicitudIntercambio;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface SolicitudIntercambioRepository extends CrudRepository<SolicitudIntercambio, Long> {

    @Query("select s from SolicitudIntercambio s  where s.estado='PENDIENTE' or s.estado='REASIGNADA'")
    List<SolicitudIntercambio> findAllPending();


    @Query("select s from SolicitudIntercambio s where s.estado='PENDIENTE' and s.empleado.id<>?1")
    List<SolicitudIntercambio> findOthersSolicitudesPending(Long id);


    @Query("select s from SolicitudIntercambio s where s.empleado.id=?1")
    List<SolicitudIntercambio> findOwnSolicitudesIntercambio(Long id);


    @Query("select s from SolicitudIntercambio s where s.fecha=?1 and s.empleado.id=?2")
    List<SolicitudIntercambio> findSolicitudIntercambioByFechaEmpleado(String date, Long id);

    @Modifying
    @Query("update SolicitudIntercambio s set s.nuevoEmpleado=?2 where s.id=?1")
    void asignarNuevoEmpleado(Long id, Empleado nuevoEmpleado);
}
