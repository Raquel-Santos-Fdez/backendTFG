package com.uniovi.repositories;

import com.uniovi.entities.Solicitud;
import com.uniovi.entities.SolicitudIntercambio;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolicitudRepository extends CrudRepository<Solicitud, Long> {

    @Modifying
    @Query("update Solicitud s set s.estado='ACEPTADA' where s.id=?1")
    void aceptarSolicitud(Long id);

    @Modifying
    @Query("update Solicitud s set s.estado='RECHAZADA' where s.id=?1")
    void rechazarSolicitud(Long id);

//    @Query("select s from Solicitud s  where s.estado='PENDIENTE'")
//    List<Solicitud> findAllPending();


}
