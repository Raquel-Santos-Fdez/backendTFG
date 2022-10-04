package com.uniovi.repositories;

import com.uniovi.entities.SolicitudVacaciones;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SolicitudVacacionesRepository extends CrudRepository<SolicitudVacaciones, Long> {

    @Query("select s from SolicitudVacaciones s where s.empleado.id=?1")
    List<SolicitudVacaciones> findByEmpleado(Long idEmpleado);

    @Query("select s from SolicitudVacaciones s where s.estado='PENDIENTE'")
    List<SolicitudVacaciones> findAllPending();
}
