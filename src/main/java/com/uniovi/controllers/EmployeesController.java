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
//
//    @PostMapping(value="/login")
//    public Employee login(@RequestBody Employee employee){
//        System.out.println("HASTA AQUI LLEGA");
//        List<Employee> employees=employeesService.getEmployeeByUsername(employee.getUsername());
//        if(!employees.isEmpty()) {
//            System.out.println("autenticado correctamente");
//            return employees.get(0);
//        }
//        System.out.println("EROORRR");
//        return null;
//    }


    @PostMapping(value="/login")
    public Employee login(@RequestHeader("Authorization") String token){
        System.out.println("token: "+token);
        System.out.println("decodificado: "+Base64.getDecoder().decode(token.split(" ")[1]));
//        List<Employee> employees=employeesService.getEmployeeByUsername(employee.getUsername());
//        if(!employees.isEmpty()) {
//            System.out.println("autenticado correctamente");
//            return employees.get(0);
//        }
//        System.out.println("EROORRR");
        return null;
    }

//    @GetMapping(value = "/login")
//    public String login() {
//        return "authenticated successfully";
//    }

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
