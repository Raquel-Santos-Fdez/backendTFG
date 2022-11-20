package com.uniovi.services;

import com.uniovi.entities.Tren;
import com.uniovi.repositories.TrenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicios encargado de la gestión de los trenes
 *
 * @author UO266047
 */
@Service
public class TrenService {

    @Autowired
    private TrenRepository trenRepository;

    /**
     * Busca un tren por id
     * @param id id del tren a buscar
     * @return Tren encontrado
     */
    public Tren findTrenById(Long id) {
        Tren tren = null;
        if (id != null && trenRepository.findById(id).isPresent())
            return trenRepository.findById(id).get();
        return tren;
    }

    /**
     * Añade un tren
     * @param tren nuevo tren
     */
    public void addTren(Tren tren) {
        if (tren != null)
            trenRepository.save(tren);
    }

    /**
     * Elimina todos los trenes
     */
    public void deleteAll() {
        trenRepository.deleteAll();
    }

    /**
     * Busca todos los trenes
     * @return Lista de trenes
     */
    public List<Tren> findAll(){
        List<Tren> trenes=new ArrayList<>();
        trenRepository.findAll().forEach(trenes::add);
        return trenes;
    }
}
