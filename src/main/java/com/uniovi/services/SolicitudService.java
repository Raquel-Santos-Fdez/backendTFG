package com.uniovi.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniovi.entities.*;
import com.uniovi.repositories.SolicitudIntercambioRepository;
import com.uniovi.repositories.SolicitudRepository;
import com.uniovi.repositories.SolicitudSimpleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    public SolicitudIntercambioRepository solicitudIntercambioRepository;

    @Autowired
    public SolicitudSimpleRepository solicitudSimpleRepository;

    public List<Solicitud> findSolicitudByFechaEmpleado(String date, Long id) {
        return solicitudRepository.findSolicitudByFechaEmpleado(date, id);
    }

    @Transactional
    public void gestionarSolicitudAceptada(Solicitud solicitud) {
        SolicitudMapper objectMapper=Mapper.convertirObjectSolicitud(solicitud);

    }

    @Transactional
    public void aceptarSolicitud(Solicitud solicitud) {
        solicitudRepository.aceptarSolicitud(solicitud.getId());
        solicitud.getEmpleado().setnDiasLibres(solicitud.getEmpleado().getnDiasLibres()-1);
    }

    @Transactional
    public void setSolicitud(SolicitudSimple solicitud) {

        SolicitudMapper solicitudMapper = Mapper.convertirObjectSolicitud(solicitud);
        solicitudSimpleRepository.save((SolicitudSimple)solicitudMapper.getSolicitudMapeada());
    }

    @Transactional
    public void rechazarSolicitud(Long id) {
        solicitudRepository.rechazarSolicitud(id);
    }

    public List<Solicitud> findOwnSolicitudes(Long id) {
        List<Solicitud> solicitudes= new ArrayList<Solicitud>();
        solicitudes.addAll(solicitudIntercambioRepository.findOwnSolicitudesIntercambio(id));
        solicitudes.addAll(solicitudSimpleRepository.findOwnSolicitudesSimples(id));
        return solicitudes;
    }

    public List<SolicitudIntercambio> findOthersSolicitudesPending(Long id) {
        return solicitudIntercambioRepository.findOthersSolicitudesPending(id);
    }


}
