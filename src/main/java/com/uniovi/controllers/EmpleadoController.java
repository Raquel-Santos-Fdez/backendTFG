package com.uniovi.controllers;

import com.uniovi.entities.Empleado;
import com.uniovi.services.EmpleadoService;
//import com.uniovi.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

//Importante cambiarlo para que el front pueda acceder a las peticiones
@RestController
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping(value = "/login")
    public Empleado login(@RequestBody Empleado empleado) {
        List<Empleado> empleados = empleadoService.findByUsernamePassword(empleado.getUsername(), empleado.getPassword());
        if (!empleados.isEmpty())
            return empleados.get(0);
        return null;
    }

    @GetMapping("/empleados")
    public List<Empleado> getEmpleados() {
        return empleadoService.getEmpleados();
    }

    @GetMapping("/empleado/{id}")
    public Empleado getEmployeeById(@PathVariable Long id) {
        return empleadoService.getEmployeeById(id).get();
    }

    @PostMapping(value = "/empleados/addEmpleado")
    public void addEmpleado(@RequestBody Empleado empleado) {
        empleadoService.addEmployee(empleado);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = "/empleados/eliminarEmpleado/{id}")
    public void eliminarEmpleado(@PathVariable Long id) {
        empleadoService.deleteEmployee(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "/empleados/actualizarEmpleado/")
    public void actualizarPassword(@RequestBody Empleado empleado) {
         empleadoService.actualizarPassword(empleado);
    }

}
