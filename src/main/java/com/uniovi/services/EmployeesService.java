package com.uniovi.services;

import com.uniovi.entities.Employee;
import com.uniovi.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeesService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
    }

    public List<Employee> getEmployees(){
        List<Employee> employees=new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    public void addEmployee(Employee employee){
        //employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

    public List<Employee> getEmployeeByUsername(String username){
        return employeeRepository.findByUsername(username);
    }
}
