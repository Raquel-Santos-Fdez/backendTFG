package com.uniovi.services;

import com.uniovi.entities.Incidencia;
import com.uniovi.repositories.IncidenciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IncidenciaService {

    @Autowired
    private IncidenciasRepository incidenciaRepository;

    public List<Incidencia> getIncidenciasPending(Long idTren) {
        return incidenciaRepository.getIncidenciasPending(idTren);
    }

    public void addInicidencia(Incidencia incidencia) {
        incidenciaRepository.save(incidencia);
    }

    public void deleteIncidencia(Long id) {
        incidenciaRepository.deleteById(id);
    }

    public List<Incidencia> getAllIncidencias() {
        List<Incidencia> incidencias = new ArrayList<>();
        incidenciaRepository.findAll().forEach(i -> incidencias.add(i));
        return incidencias;
    }
}
