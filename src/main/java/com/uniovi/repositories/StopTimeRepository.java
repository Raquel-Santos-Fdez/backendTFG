package com.uniovi.repositories;

import com.uniovi.entities.Stop_time;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StopTimeRepository extends CrudRepository<Stop_time, Long> {

        @Query("select distinct s from stop_times s join s.trip t join t.route r join s.stop st " +
            "where r.route_id=?1 and st.stop_name LIKE ?2 order by s.arrival_time")
    List<Stop_time> findStopTimeByRouteId(String id, String nombreParcial);


    @Query("select st from stop_times st join st.trip t join t.route r join st.stop s where s.stop_id=st.stop.stop_id and r.route_id=?1" +
            " and st.trip.trip_id=t.trip_id and t.route.route_id=r.route_id and s.stop_id=?2 group by st.arrival_time")
    List<Stop_time> findStopTimeByRouteStop(String routeId, String stopId);
}
