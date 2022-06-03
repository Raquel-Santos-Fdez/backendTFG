package com.uniovi.repositories;

import com.uniovi.entities.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, String> {
}
