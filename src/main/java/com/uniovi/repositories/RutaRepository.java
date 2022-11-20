package com.uniovi.repositories;

import com.uniovi.entities.Ruta_estacion;
import com.uniovi.entities.Ruta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RutaRepository extends CrudRepository<Ruta, String> {

    @Query("select rs from Ruta_estacion rs where rs.estacion.id=?1 and rs.ruta.id in " +
            "(select rs2.ruta.id from Ruta_estacion rs2 where rs2.estacion.id=?2)")
    List<Ruta_estacion> findRutaByEstaciones(String origenId, String destinoId);
}
