package com.uniovi.services;


import com.fasterxml.jackson.annotation.JsonTypeInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.uniovi.entities.Solicitud;
import com.uniovi.entities.SolicitudMapper;

public class Mapper {

    public static SolicitudMapper convertirObjectSolicitud(Solicitud solicitud){
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        SolicitudMapper solicitudMapper=new SolicitudMapper();
        solicitudMapper.setSolicitudMapeada(solicitud);
        try {
            String jsonDataString=objectMapper.writeValueAsString(solicitudMapper);
            SolicitudMapper deserializedSolicitud=objectMapper.readValue(jsonDataString, SolicitudMapper.class);
            return deserializedSolicitud;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return solicitudMapper;
    }
}
