package com.uniovi.services;

import com.uniovi.entities.Incidencia;
import com.uniovi.repositories.IncidenciasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenciasService {

    @Autowired
    private IncidenciasRepository incidenciaRepository;

    public List<Incidencia> getIncidenciasPending(Long id) {
        return incidenciaRepository.getIncidenciasPending(id);
    }

    public void addInicidencia(Incidencia incidencia) {
        incidenciaRepository.save(incidencia);
    }
}
