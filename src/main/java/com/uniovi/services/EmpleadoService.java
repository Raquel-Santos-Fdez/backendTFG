package com.uniovi.services;

import com.uniovi.entities.Empleado;
import com.uniovi.repositories.EmpleadoRepository;
import com.uniovi.validators.ArgumentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicios encargado de la gestión de los empleados
 *
 * @author UO266047
 */
@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    /**
     * Busca todos los empleados
     * @return Lista de los empleados
     */
    public List<Empleado> getEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        empleadoRepository.findAll().forEach(empleados::add);
        return empleados;
    }

    /**
     * Añade un empleado
     * @param empleado Empleado a añadir
     */
    public void addEmpleado(Empleado empleado) {
        if (checkValues(empleado))
            empleadoRepository.save(empleado);
    }

    /**
     * Comprueba que no existe otro empleado en el sistema con ese nombre de usuario
     * @param empleado
     * @return true en caso de que no exista, false en caso de que exista
     */
    private boolean checkValues(Empleado empleado) {
        if(findByUsername(empleado.getUsername())!=null)
            return false;
//        if(empleado.getEmail())
        return true;
    }

    /**
     * Busca un empleado por nombre de usuario
     * @param username Nombre de usuario del empleado
     * @return Empleado encontrado o null
     */
    public Empleado findByUsername(String username) {
        return empleadoRepository.findByUsername(username);
    }

    /**
     * Elimina un empleado dado su id
     * @param idEmpleado id del empleado
     */
    public void deleteEmpleado(Long idEmpleado) {
        empleadoRepository.deleteById(idEmpleado);
    }

    /**
     * Busca empleados por nombre de usuario y contraseña
     * @param username nombre de usuario
     * @param password contraseña
     * @return Empleado que coincide con las características
     */
    public Empleado findByUsernamePassword(String username, String password) {
        return empleadoRepository.findByUsernamePassword(username, password);
    }

    /**
     * Busca empleados por su id
     * @param idEmpleado id del empleado
     * @return Empleado encontrado o null
     */
    public Empleado getEmpleadoById(Long idEmpleado) {
        return empleadoRepository.findById(idEmpleado).get();
    }

    /**
     * Actualiza la contraseña del empleado
     * @param empleado Empleado con la nueva contaseña
     */
    public void actualizarPassword(Empleado empleado) {
        empleadoRepository.actualizarPassword(empleado.getId(), empleado.getPassword());
    }
}
