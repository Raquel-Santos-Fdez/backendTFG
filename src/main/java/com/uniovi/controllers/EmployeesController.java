package com.uniovi.controllers;

import com.uniovi.entities.Employee;
import com.uniovi.services.EmployeesService;
//import com.uniovi.services.SecurityService;
import com.uniovi.validators.SingUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

import java.net.http.HttpHeaders;
import java.util.Base64;
import java.util.List;

//Importante cambiarlo para que el front pueda acceder a las peticiones
@RestController
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @RequestMapping("/employee/list")
    public String getList(Model model){
        model.addAttribute("employeesList", employeesService.getEmployees());
        return "employee/list";
    }

    @PostMapping(value="/login")
    public Employee login(@RequestBody Employee employee){
        List<Employee> employees=employeesService.findByUsernamePassword(employee.getUsername(), employee.getPassword());
        if(!employees.isEmpty())
            return employees.get(0);
        return null;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeesService.getEmployees();
    }
}
