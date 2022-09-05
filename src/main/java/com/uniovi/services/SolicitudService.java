package com.uniovi.services;

import com.uniovi.entities.Jornada;
import com.uniovi.entities.Solicitud;
import com.uniovi.repositories.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    public List<Solicitud> findSolicitudByFechaEmpleado(String date, Long id) {
        return solicitudRepository.findSolicitudByFechaEmpleado(date, id);
    }
}
