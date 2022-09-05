package com.uniovi.repositories;

import com.uniovi.entities.Solicitud;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface SolicitudRepository extends CrudRepository<Solicitud, Long> {

    @Modifying
    @Query("update Solicitud s set s.estado='ACEPTADA' where s.id=?1")
    void aceptarSolicitud(Long id);

    @Modifying
    @Query("update Solicitud s set s.estado='RECHAZADA' where s.id=?1")
    void rechazarSolicitud(Long id);

    @Modifying
    @Query("update Solicitud s set s.estado='REASIGNADA' where s.id=?1")
    void reasignar(Long id);

    @Query("select s from Solicitud s where s.fecha=?1 and s.empleado.id=?2")
    List<Solicitud> findSolicitudByFechaEmpleado(String date, Long id);


}
