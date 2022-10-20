package com.uniovi.config;


import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.uniovi.entities.Solicitud;

public class Mapper {

    public static SolicitudMapper convertirObjectSolicitud(Solicitud solicitud){
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        SolicitudMapper solicitudMapper=new SolicitudMapper();
        solicitudMapper.setSolicitudMapeada(solicitud);
        try {
            String jsonDataString=objectMapper.writeValueAsString(solicitudMapper);
            return objectMapper.readValue(jsonDataString, SolicitudMapper.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return solicitudMapper;
    }
}
