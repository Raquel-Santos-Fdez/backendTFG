package com.uniovi.repositories;

import com.uniovi.entities.Horario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HorarioRepository extends CrudRepository<Horario, Long> {

    @Query("select st from horario st join st.trayecto t join t.ruta r join st.estacion s where s.id=st.estacion.id and r.id=?1" +
            " and st.trayecto.id=t.id and t.ruta.id=r.id and s.id=?2 group by st.hora_llegada")
    List<Horario> findHorariosByRutaEstacion(String rutaId, String estacionId);
}
