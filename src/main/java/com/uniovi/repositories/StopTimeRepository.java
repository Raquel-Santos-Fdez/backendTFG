package com.uniovi.repositories;

import com.uniovi.entities.Stop_time;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StopTimeRepository extends CrudRepository<Stop_time, Long> {

        @Query("select distinct s from stop_times s join s.trip t join t.ruta r join s.estacion st " +
            "where r.ruta_id=?1 and st.nombre LIKE ?2 order by s.arrival_time")
    List<Stop_time> findStopTimeByRouteId(String id, String nombreParcial);


    @Query("select st from stop_times st join st.trip t join t.ruta r join st.estacion s where s.id=st.estacion.id and r.ruta_id=?1" +
            " and st.trip.trip_id=t.trip_id and t.ruta.ruta_id=r.ruta_id and s.id=?2 group by st.arrival_time")
    List<Stop_time> findStopTimeByRouteStop(String routeId, String stopId);
}
