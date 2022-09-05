package com.uniovi.repositories;

import com.uniovi.entities.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {

    @Query("select e from Empleado e where e.username=?1")
    List<Empleado> findByUsername(String username);

    @Query("select e from Empleado e where e.username=?1 and e.password=?2")
    List<Empleado> findByUsernamePassword(String username, String password);
}
