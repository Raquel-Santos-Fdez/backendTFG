package com.uniovi.repositories;

import com.uniovi.entities.Solicitud;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SolicitudRepository extends CrudRepository<Solicitud, Long> {

    @Modifying
    @Query("update Solicitud s set s.estado='RECHAZADA' where s.id=?1")
    void rechazarSolicitud(Long id);


}
