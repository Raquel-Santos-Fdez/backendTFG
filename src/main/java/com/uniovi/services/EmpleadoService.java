package com.uniovi.services;

import com.uniovi.entities.Empleado;
import com.uniovi.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
    }

    public List<Empleado> getEmpleados(){
        List<Empleado> empleados =new ArrayList<>();
        empleadoRepository.findAll().forEach(empleados::add);
        return empleados;
    }

    public void addEmployee(Empleado empleado){
        empleadoRepository.save(empleado);
    }

    public void deleteEmployee(Long id){
        empleadoRepository.deleteById(id);
    }

    public List<Empleado> getEmployeeByUsername(String username){
        return empleadoRepository.findByUsername(username);
    }

    public List<Empleado> findByUsernamePassword(String username, String password) {
        return empleadoRepository.findByUsernamePassword(username,password);
    }


    public Optional<Empleado> getEmployeeById(Long id) {
        return empleadoRepository.findById(id);
    }


    public void actualizarPassword(Empleado empleado) {
        empleadoRepository.actualizarPassword(empleado.getId(), empleado.getPassword());
    }
}
