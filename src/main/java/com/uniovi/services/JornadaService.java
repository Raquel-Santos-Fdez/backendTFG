package com.uniovi.services;

import com.uniovi.entities.*;
import com.uniovi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JornadaService {

    @Autowired
    public JornadaRepository jornadaRepository;

    @Autowired
    public SolicitudIntercambioRepository solicitudIntercambioRepository;

    @Autowired
    public SolicitudSimpleRepository solicitudSimpleRepository;

    @Autowired
    public SolicitudRepository solicitudRepository;

    @Autowired
    public TareaRepository tareaRepository;


    public List<Tarea> getJornadaByDateAndEmpleoyee(Long id, Date date) {
        List<Tarea> jornadas=jornadaRepository.findByDateAndEmployee(id, new java.sql.Date(date.getTime()));
        return jornadas;
    }

    public void setSolicitud(SolicitudSimple solicitud) {
        solicitudSimpleRepository.save(solicitud);
    }

    public List<Solicitud> getAllSolicitudesPendientes() {
        List<Solicitud> solicitudes=new ArrayList<>();
        solicitudes.addAll(solicitudIntercambioRepository.findAllPending());
        solicitudes.addAll(solicitudSimpleRepository.findAllPending());
        return solicitudes;
    }

    @Transactional
    public void aceptarSolicitud(Long id) {
        solicitudRepository.aceptarSolicitud(id);
    }

    @Transactional
    public void rechazarSolicitud(Long id) {
        solicitudRepository.rechazarSolicitud(id);
    }

    public void addTarea(Tarea t) {
        tareaRepository.save(t);
    }

    public Stop findStopByTarea(Long id) {
       return tareaRepository.findStopByTarea(id).get(0);
    }

    public List<SolicitudIntercambio> findOthersSolicitudesPending(Long id) {
        return solicitudIntercambioRepository.findOthersSolicitudesPending(id);
    }

    public List<SolicitudIntercambio> findOwnSolicitudes(Long id) {
        return solicitudIntercambioRepository.findOwnSolicitudes(id);
    }

    public void addSolicitudIntercambio(SolicitudIntercambio solicitud) {
        solicitudIntercambioRepository.save(solicitud);
    }
}
