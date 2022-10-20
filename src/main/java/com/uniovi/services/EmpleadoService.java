package com.uniovi.services;

import com.uniovi.entities.Empleado;
import com.uniovi.repositories.EmpleadoRepository;
import com.uniovi.validators.ArgumentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> getEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleados::add);
        return empleados;
    }

    public void addEmpleado(Empleado empleado) {
        if (checkValues(empleado))
            empleadoRepository.save(empleado);
    }

    private boolean checkValues(Empleado empleado) {
        if(findByUsername(empleado.getUsername())!=null)
            return false;
//        if(empleado.getEmail())
        return true;
    }

    public Empleado findByUsername(String username) {
        return empleadoRepository.findByUsername(username);
    }

    public void deleteEmpleado(Long id) {
        empleadoRepository.deleteById(id);
    }

    public List<Empleado> findByUsernamePassword(String username, String password) {
        return empleadoRepository.findByUsernamePassword(username, password);
    }


    public Optional<Empleado> getEmpleadoById(Long id) {
        return empleadoRepository.findById(id);
    }


    public void actualizarPassword(Empleado empleado) {
        empleadoRepository.actualizarPassword(empleado.getId(), empleado.getPassword());
    }
}
