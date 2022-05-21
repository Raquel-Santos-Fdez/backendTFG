package com.uniovi.controllers;

import com.uniovi.entities.Employee;
import com.uniovi.services.EmployeesService;
//import com.uniovi.services.SecurityService;
import com.uniovi.validators.SingUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        return "login";
    }

//    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
//    public String home(Model model) {
//        //El obj Authentication almacena toda la info del usuario autenticado
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        //obtenemos el username que es el dni en nuestro caso
//        String username = auth.getName();
//        //obtenemos toda la info del usuario
//        Employee activeUser = employeesService.getEmployeeByUsername(username);
//        //obtenemos el conjunto de calificaciones y las guardamos en marklist
//       // model.addAttribute("markList", activeUser.getMarks());
//        return "home";
//    }

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeesService.getEmployees();
    }
}
