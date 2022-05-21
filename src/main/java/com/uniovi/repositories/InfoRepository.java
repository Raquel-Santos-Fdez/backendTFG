package com.uniovi.repositories;


import com.uniovi.entities.Ruta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InfoRepository extends CrudRepository<Ruta, Long> {

}
