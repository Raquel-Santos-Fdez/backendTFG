package com.uniovi.repositories;

import com.uniovi.entities.Solicitud;
import com.uniovi.entities.SolicitudIntercambio;
import com.uniovi.entities.SolicitudSimple;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface SolicitudSimpleRepository extends CrudRepository<SolicitudSimple, Long> {

    @Query("select s from SolicitudSimple s  where s.estado='PENDIENTE'")
    List<SolicitudSimple> findAllPending();
}
