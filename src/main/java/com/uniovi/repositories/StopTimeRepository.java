package com.uniovi.repositories;

import com.uniovi.entities.Stop_time;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StopTimeRepository extends CrudRepository<Stop_time, Long> {

    @Query("select distinct s from stop_times s join s.trip t join t.route r join s.stop st " +
            "where r.route_id=?1 and st.stop_name LIKE ?2 order by s.arrival_time")
    List<Stop_time> findStopTimeByRouteId(String id, String nombreParcial);
}
