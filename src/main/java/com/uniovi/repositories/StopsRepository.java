package com.uniovi.repositories;

import com.uniovi.entities.Route;
import com.uniovi.entities.Stop;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StopsRepository extends  CrudRepository<Stop, String> {


}
