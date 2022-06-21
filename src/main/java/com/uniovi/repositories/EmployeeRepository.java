package com.uniovi.repositories;

import com.uniovi.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query("select e from Employee e where e.username=?1")
    List<Employee> findByUsername(String username);

    @Query("select e from Employee e where e.username=?1 and e.password=?2")
    List<Employee> findByUsernamePassword(String username, String password);
}
