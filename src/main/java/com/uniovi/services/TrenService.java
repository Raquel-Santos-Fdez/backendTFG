package com.uniovi.services;

import com.uniovi.entities.Tren;
import com.uniovi.repositories.TrenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrenService {

    @Autowired
    private TrenRepository trenRepository;

    public Tren findTrenById(Long id) {
        Tren tren = null;
        if (id != null && trenRepository.findById(id).isPresent())
            return trenRepository.findById(id).get();
        return tren;
    }

    public void addTren(Tren tren) {
        if (tren != null)
            trenRepository.save(tren);
    }

    public void eliminarTodos() {
        trenRepository.deleteAll();
    }

    public List<Tren> findAll(){
        List<Tren> trenes=new ArrayList<>();
        trenRepository.findAll().forEach(trenes::add);
        return trenes;
    }
}
