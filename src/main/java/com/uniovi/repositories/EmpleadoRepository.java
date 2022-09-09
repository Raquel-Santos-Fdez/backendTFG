package com.uniovi.repositories;

import com.uniovi.entities.Empleado;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {

    @Query("select e from Empleado e where e.username=?1")
    List<Empleado> findByUsername(String username);

    @Query("select e from Empleado e where e.username=?1 and e.password=?2")
    List<Empleado> findByUsernamePassword(String username, String password);

    @Modifying
    @Transactional
    @Query("update Empleado e set e.password=?2 where e.id=?1")
    void actualizarPassword(Long id, String password);
}
