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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    public SolicitudIntercambioRepository solicitudIntercambioRepository;

    @Autowired
    public SolicitudSimpleRepository solicitudSimpleRepository;

    @Autowired
    public JornadaService jornadaService;

    public List<Solicitud> findSolicitudByFechaEmpleado(String date, Long id) {
        List<Solicitud> solicitudes=new ArrayList<>();
        solicitudes.addAll(solicitudIntercambioRepository.findSolicitudIntercambioByFechaEmpleado(date,id));
        solicitudes.addAll(solicitudSimpleRepository.findSolicitudSimpleByFechaEmpleado(date,id));
        return solicitudes;
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
        List<SolicitudIntercambio> solicitudesIntercambio=new ArrayList<>();
        List<SolicitudIntercambio> solicitudesPending=solicitudIntercambioRepository.findOthersSolicitudesPending(id);

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
        Date fechaDescanso=null;

        for(SolicitudIntercambio s:solicitudesPending){
            try {
                fecha= sdf.parse(s.getFecha());
                fechaDescanso=sdf.parse(s.getFechaDescanso());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if((jornadaService.findJornadaByDateEmployee(fechaDescanso, id).size()>0) &&
                    jornadaService.findJornadaByDateEmployee(fecha,id).size()==0)
                solicitudesIntercambio.add(s);
        }
        return solicitudesIntercambio;

    }


}
