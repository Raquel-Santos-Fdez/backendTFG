package com.uniovi.repositories;

import com.uniovi.entities.SolicitudVacaciones;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SolicitudVacacionesRepository extends CrudRepository<SolicitudVacaciones, Long> {

    @Query("select s from SolicitudVacaciones s where s.empleado.id=?1 order by s.estado")
    List<SolicitudVacaciones> findByEmpleado(Long idEmpleado);

    @Query("select s from SolicitudVacaciones s where s.estado='PENDIENTE' order by s.empleado.nDiasLibres desc")
    List<SolicitudVacaciones> findAllPending();

    @Query("select s from SolicitudVacaciones s where s.fecha=?1 and s.empleado.id=?2 and s.estado<>'RECHAZADA'")
    List<SolicitudVacaciones> findNotRechazadas(String fecha, Long idEmpleado);

    @Query("select s from SolicitudVacaciones s where s.empleado.id=?1 and s.estado='PENDIENTE'")
    List<SolicitudVacaciones> findSolicitudesVacacionesPendientes(Long idEmpleado);
}
