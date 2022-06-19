package com.uniovi.repositories;

import com.uniovi.entities.Incidencia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IncidenciasRepository extends CrudRepository<Incidencia, Long> {

    @Query("select i from Incidencia i join i.tren t where t.id=?1 and i.estado='PENDIENTE'")
    List<Incidencia> getIncidenciasPending(Long id);

}
