package com.uniovi.repositories;

import com.uniovi.entities.Empleado;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long> {

    @Modifying
    @Transactional
    @Query("update Empleado e set e.password=?2 where e.id=?1")
    void actualizarPassword(Long id, String password);

    @Query("select e from Empleado e where e.username=?1")
    Empleado findByUsername(String username);

    @Query("select e from Empleado e where e.email=?1")
    Empleado findByEmail(String email);

    @Query("select e from Empleado e where e.dni=?1")
    Empleado findByDni(String dni);
}
