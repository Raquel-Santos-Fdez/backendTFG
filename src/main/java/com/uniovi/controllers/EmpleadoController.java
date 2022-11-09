package com.uniovi.controllers;

import com.uniovi.entities.Empleado;
import com.uniovi.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Importante cambiarlo para que el front pueda acceder a las peticiones
@RestController
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;



    @GetMapping("/empleados")
    public List<Empleado> getEmpleados() {
        return empleadoService.getEmpleados();
    }

    @GetMapping("/empleado/{id}")
    public Empleado getEmpleadoById(@PathVariable Long id) {
        return empleadoService.getEmpleadoById(id);
    }

    @PostMapping(value = "/login")
    public Empleado login(@RequestBody Empleado empleado) {
        return empleadoService.findByUsernamePassword(empleado.getUsername(), empleado.getPassword());
    }

    @PostMapping(value = "/empleados/addEmpleado")
    public void addEmpleado(@RequestBody Empleado empleado) {
        empleadoService.addEmpleado(empleado);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(value = "/empleados/eliminarEmpleado/{id}")
    public void eliminarEmpleado(@PathVariable Long id) {
        empleadoService.deleteEmpleado(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "/empleados/actualizarEmpleado/")
    public void actualizarPassword(@RequestBody Empleado empleado) {
         empleadoService.actualizarPassword(empleado);
    }

}
