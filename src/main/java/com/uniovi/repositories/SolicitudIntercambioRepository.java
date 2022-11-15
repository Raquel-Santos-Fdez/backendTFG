package com.uniovi.repositories;

import com.uniovi.entities.Empleado;
import com.uniovi.entities.SolicitudIntercambio;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolicitudIntercambioRepository extends CrudRepository<SolicitudIntercambio, Long> {


    @Query("select s from SolicitudIntercambio s where s.estado='PENDIENTE' and s.empleado.id<>?1 and s.empleado.role=?2")
    List<SolicitudIntercambio> findOthersSolicitudesPendientes(Long idEmpleado, Empleado.Rol rol);


    @Query("select s from SolicitudIntercambio s where s.empleado.id=?1")
    List<SolicitudIntercambio> findOwnSolicitudesIntercambio(Long idEmpleado);


    @Query("select s from SolicitudIntercambio s where s.fecha=?1 and s.empleado.id=?2")
    List<SolicitudIntercambio> findSolicitudIntercambioByFechaEmpleado(String date, Long idEmpleado);

    @Modifying
    @Query("update SolicitudIntercambio s set s.nuevoEmpleado=?2 where s.id=?1")
    void asignarNuevoEmpleado(Long id, Empleado nuevoEmpleado);

    @Query("select s from SolicitudIntercambio s where s.fecha=?1 and s.empleado.id=?2 and s.estado<>'RECHAZADA'")
    List<SolicitudIntercambio> findNotRechazadas(String fecha, Long idEmpleado);

    @Query("select s from SolicitudIntercambio s where s.nuevoEmpleado.id=?1")
    List<SolicitudIntercambio> findByNuevoEmpleado(Long idEmpleado);
}