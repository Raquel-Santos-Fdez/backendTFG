package com.uniovi.services;

import com.uniovi.repositories.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TareaStopsService {

    @Autowired
    private TareaRepository tareaStopsRepository;


}
