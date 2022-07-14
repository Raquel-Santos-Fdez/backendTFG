package com.uniovi.repositories;

import com.uniovi.entities.Route;
import com.uniovi.entities.Route_stop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RutaRepository extends CrudRepository<Route, String> {

    @Query("select rs.route from Route_stop rs where rs.stop.stop_id=?1")
    List<Route> getRoutesByStop(String id);

    @Query("select rs from Route_stop rs where rs.stop.stop_id=?1 and rs.route.route_id in " +
            "(select rs2.route.route_id from Route_stop rs2 where rs2.stop.stop_id=?2)")
    List<Route_stop> findRutaByEstaciones(String origenId, String destinoId);
}
