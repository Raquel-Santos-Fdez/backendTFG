package com.uniovi.repositories;

import com.uniovi.entities.Stop_time;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StopTimeRepository extends CrudRepository<Stop_time, Long> {

    @Query("select st from stop_times st join st.trip t join t.ruta r join st.estacion s where s.id=st.estacion.id and r.id=?1" +
            " and st.trip.id=t.id and t.ruta.id=r.id and s.id=?2 group by st.arrival_time")
    List<Stop_time> findStopTimeByRouteStop(String routeId, String stopId);
}
