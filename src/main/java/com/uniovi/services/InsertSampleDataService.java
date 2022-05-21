package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uniovi.entities.Employee;

//@Service
public class InsertSampleDataService {

    @Autowired
    private EmployeesService usersService;

    @PostConstruct
    public void init() {
        Employee user1 = new Employee("99999990A", "Pedro", "Díaz");
        user1.setPassword("123456");
        Employee user2 = new Employee("99999991B", "Lucas", "Núñez");
        user2.setPassword("123456");
        Employee user3 = new Employee("99999992C", "María", "Rodríguez");
        user3.setPassword("123456");
        Employee user4 = new Employee("99999993D", "Marta", "Almonte");
        user4.setPassword("123456");
        Employee user5 = new Employee("99999977E", "Pelayo", "Valdes");
        user5.setPassword("123456");
        Employee user6 = new Employee("99999988F", "Edward", "Núñez");
        user6.setPassword("123456");

        usersService.addEmployee(user1);
        usersService.addEmployee(user2);
        usersService.addEmployee(user3);
        usersService.addEmployee(user4);
        usersService.addEmployee(user5);
        usersService.addEmployee(user6);
    }
}
