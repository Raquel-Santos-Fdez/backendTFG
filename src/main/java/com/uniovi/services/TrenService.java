package com.uniovi.services;

import com.uniovi.entities.Tren;
import com.uniovi.repositories.TrenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrenService {

    @Autowired
    private TrenRepository trenRepository;

    public Tren findTrenById(Long id) {
        return trenRepository.findById(id).get();
    }
}
