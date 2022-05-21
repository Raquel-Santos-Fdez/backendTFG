package com.uniovi.services;

import com.uniovi.entities.Ruta;
import com.uniovi.repositories.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfoService {

    @Autowired
    private InfoRepository infoRepository;


    public List<Ruta> getRutas() {
        List<Ruta> rutas=new ArrayList<>();
        for(Ruta r:infoRepository.findAll())
            rutas.add(r);
        return rutas;
    }
}
