package com.uniovi.repositories;

import com.uniovi.entities.Ruta;
import com.uniovi.entities.Route_stop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RutaRepository extends CrudRepository<Ruta, String> {

    @Query("select rs.ruta from Route_stop rs where rs.estacion.id=?1")
    List<Ruta> getRoutesByStop(String id);

    @Query("select rs from Route_stop rs where rs.estacion.id=?1 and rs.ruta.ruta_id in " +
            "(select rs2.ruta.ruta_id from Route_stop rs2 where rs2.estacion.id=?2)")
    List<Route_stop> findRutaByEstaciones(String origenId, String destinoId);
}
