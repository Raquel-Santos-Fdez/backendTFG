package com.uniovi.unit;

import com.uniovi.entities.Empleado;
import com.uniovi.services.EmpleadoService;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpleadoTest {

    @Autowired
    private EmpleadoService empleadoService;

    @Before
    public void antesDeCadaTest() {
        empleadoService.getEmpleados().forEach(s -> empleadoService.deleteEmpleado(s.getId()));
    }

    @After
    public void despuesDeCadaTest(){
        empleadoService.getEmpleados().forEach(s -> empleadoService.deleteEmpleado(s.getId()));
    }

    @Test
    public void pr01AddEmpleadoValidoTest() {
        Empleado empleado = new Empleado("admin1", "Administrador", "Prueba", "admin1@gmail.com",
                "11111111A", "TestPassword1", Empleado.Rol.ADMIN, 100);
        empleadoService.addEmpleado(empleado);
        assertEquals(empleadoService.getEmpleados().size(), 1);
    }

    @Test
    public void pr02AddEmpleadoRepetidoTest() {
        Empleado empleado = new Empleado("admin1", "Administrador", "Prueba", "admin1@gmail.com",
                "11111111A", "TestPassword1", Empleado.Rol.ADMIN, 100);
        empleadoService.addEmpleado(empleado);
        Empleado empleado2 = new Empleado("admin1", "Administrador", "Prueba", "admin1@gmail.com",
                "11111111A", "TestPassword1", Empleado.Rol.ADMIN, 100);
        empleadoService.addEmpleado(empleado2);
        assertEquals(empleadoService.getEmpleados().size(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pr03addEmpleadoNoValido() {

        empleadoService.addEmpleado(new Empleado("admin2", "Administrador", "Prueba", "admin2",
                "11A", "Admin", Empleado.Rol.ADMIN, 100));
    }

    @Test
    public void pr04getEmpleadosTest() {
        Empleado empleado1 = new Empleado("admin1", "Administrador", "Prueba", "admin1@gmail.com",
                "11111111A", "TestPassword1", Empleado.Rol.ADMIN, 100);
        Empleado empleado2 = new Empleado("admin2", "Administrador2", "Prueba2", "admin2@gmail.com",
                "22222222A", "TestPassword2", Empleado.Rol.ADMIN, 100);
        empleadoService.addEmpleado(empleado1);
        empleadoService.addEmpleado(empleado2);

        assertEquals(empleadoService.getEmpleados().size(), 2);
    }

    @Test
    public void pr05findByUsernamePasswordTest() {
        Empleado empleado1 = new Empleado("admin1", "Administrador", "Prueba", "admin1@gmail.com",
                "11111111A", "TestPassword1", Empleado.Rol.ADMIN, 100);
        empleadoService.addEmpleado(empleado1);
        assertNotNull(empleadoService.findByUsernamePassword("admin1", "TestPassword1"));
        assertEquals(empleadoService.findByUsernamePassword("admin1", "TestPassword1").size(), 1);

    }

    @Test
    public void pr06eliminarEmpleadoExistenteTest() {
        Empleado empleado = new Empleado("admin1", "Administrador", "Prueba", "admin1@gmail.com",
                "11111111A", "TestPassword1", Empleado.Rol.ADMIN, 100);
        empleadoService.addEmpleado(empleado);
        Empleado empleadoBd = empleadoService.findByUsername("admin1");

        empleadoService.deleteEmpleado(empleadoBd.getId());
        assertEquals(empleadoService.getEmpleados().size(), 0);
    }

    @Test(expected = Exception.class)
    public void pr07eliminarEmpleadoNoExistenteTest() {

        empleadoService.deleteEmpleado(Long.parseLong("10"));

        assertEquals(empleadoService.getEmpleados().size(), 0);
    }


}
