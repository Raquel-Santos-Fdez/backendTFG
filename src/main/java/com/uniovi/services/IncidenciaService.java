package com.uniovi.services;

import com.uniovi.entities.Incidencia;
import com.uniovi.repositories.IncidenciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicios encargado de la gestión de las incidencias
 *
 * @author UO266047
 */
@Service
public class IncidenciaService {

    @Autowired
    private IncidenciasRepository incidenciaRepository;

    /**
     * Busca las incidencias con estado "PENDIENTE"
     * @param idTren id del tren
     * @return devuelve la lista de incidencias con estado pendie
     */
    public List<Incidencia> getIncidenciasPending(Long idTren) {
        return incidenciaRepository.getIncidenciasPending(idTren);
    }

    /**
     * Añade una incidencia
     * @param incidencia incidencia a añadir
     */
    public void addInicidencia(Incidencia incidencia) {
        incidenciaRepository.save(incidencia);
    }

    /**
     * Elimina una incidencia
     * @param id id de la incidencia a eliminar
     */
    public void deleteIncidencia(Long id) {
        incidenciaRepository.deleteById(id);
    }

    /**
     * Busca todas las incidencias
     * @return Lista con todas las incidencias
     */
    public List<Incidencia> getAllIncidencias() {
        List<Incidencia> incidencias = new ArrayList<>();
        incidenciaRepository.findAll().forEach(i -> incidencias.add(i));
        return incidencias;
    }
}
