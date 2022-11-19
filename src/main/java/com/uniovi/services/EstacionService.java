package com.uniovi.services;

import com.uniovi.entities.Estacion;
import com.uniovi.entities.Horario;
import com.uniovi.entities.Trayecto;
import com.uniovi.repositories.EstacionRepository;
import com.uniovi.repositories.HorarioRepository;
import com.uniovi.repositories.TrayectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicios encargado de la gestión de las estaciones
 *
 * @author UO266047
 */
@Service
public class EstacionService {


    @Autowired
    private EstacionRepository estacionRepository;

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private TrayectoRepository trayectoRepository;

    /**
     * Añade una nueva estación
     * @param estacion estacion a añadir
     */
    public void addEstacion(Estacion estacion) {
        estacionRepository.save(estacion);
    }

    /**
     * Obtiene todas las estaciones
     * @return lista que contiene todas las estaciones
     */
    public List<Estacion> getEstaciones() {
        List<Estacion> estacions = new ArrayList<>();
        estacionRepository.findAll().forEach(estacions::add);
        return estacions;
    }

    /**
     * Busca una estacion por id
     * @param id id de la estacion a buscar
     * @return estacion encontrada o null
     */
    public Estacion getEstacionById(String id) {
        Optional<Estacion> stop = estacionRepository.findById(id);
        if (stop.isPresent())
            return stop.get();
        return null;
    }

    /**
     * Añade un nuevo horario
     * @param st nuevo horario a añadir
     */
    public void addHorario(Horario st) {
        horarioRepository.save(st);
    }

    /**
     * Añade un nuevo trayecto
     * @param trayecto nuevo trayecto a añadir
     */
    public void addTrayecto(Trayecto trayecto) {
        trayectoRepository.save(trayecto);
    }

    /**
     * Busca un trayecto por id
     * @param id id del trayecto a encontrar
     * @return trayecto encontrado o null
     */
    public Trayecto getTrayectoById(String id) {
        Optional<Trayecto> trayecto = trayectoRepository.findById(id);
        if (trayecto.isPresent())
            return trayecto.get();
        return null;
    }

    /**
     * Obtiene los horarios de una ruta que incluye una estacion
     * @param rutaId id de la ruta
     * @param estacionId id de la estacion
     * @return lista de los horarios
     */
    public List<Horario> findHorarioByRutaEstacion(String rutaId, String estacionId) {
        return horarioRepository.findHorariosByRutaEstacion(rutaId, estacionId);
    }

    /**
     * Elimina una estacion
     * @param id id de la estacion a eliminar
     */
    public void deleteEstacion(String id) {
        if (getEstacionById(id) != null)
            estacionRepository.deleteById(id);
    }
}
