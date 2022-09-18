package com.uniovi.services;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.uniovi.entities.*;
import com.uniovi.repositories.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    public List<Solicitud> findSolicitudByFechaEmpleado(String date, Long id) {
        return solicitudRepository.findSolicitudByFechaEmpleado(date, id);
    }

    @Transactional
    public void gestionarSolicitudAceptada(Solicitud solicitud) {
        PolymorphicTypeValidator ptv= BasicPolymorphicTypeValidator.builder()
                .allowIfBaseType("com.baeldung.jackson.inheritance")
                .allowIfSubType("java.util.ArrayList")
                .build();
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);

        SolicitudMapper solicitudMapper=new SolicitudMapper();
        solicitudMapper.setSolicitudMapeada(solicitud);
        try {
            String jsonDataString=objectMapper.writeValueAsString(solicitudMapper);
            SolicitudMapper deserializedSolicitud=objectMapper.readValue(jsonDataString, SolicitudMapper.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Transactional
    public void aceptarSolicitud(Solicitud solicitud) {
        solicitudRepository.aceptarSolicitud(solicitud.getId());
        solicitud.getEmpleado().setnDiasLibres(solicitud.getEmpleado().getnDiasLibres()-1);
    }

    @Transactional
    public void rechazarSolicitud(Long id) {
        solicitudRepository.rechazarSolicitud(id);
    }


}
