package com.uniovi.unit;

import com.uniovi.entities.Empleado;
import com.uniovi.entities.Jornada;
import com.uniovi.entities.Tarea;
import com.uniovi.services.EmpleadoService;
import com.uniovi.services.JornadaService;
import com.uniovi.services.TareaService;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TareaTest {

    @Autowired
    private JornadaService jornadaService;

    @Autowired
    private TareaService tareaService;

    @Autowired
    private EmpleadoService empleadoService;

    @Before
    public void antesDeCadaTest() {
        jornadaService.eliminarTodos();
        empleadoService.getEmpleados().forEach(s -> empleadoService.deleteEmpleado(s.getId()));
    }

    @After
    public void despuesDeCadaTest() {
        jornadaService.eliminarTodos();
        empleadoService.getEmpleados().forEach(s -> empleadoService.deleteEmpleado(s.getId()));
    }

    @Test
    public void pr01findTareaByIdTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2022-10-17");
            Jornada jornada = new Jornada(fecha, empleado, false);

            Tarea tarea = new Tarea();
            Set<Tarea> tareas = new HashSet<>();
            tareas.add(tarea);
            jornada.setTareas(tareas);

            jornadaService.addJornada(jornada);

            assertNotNull(tareaService.findTareaById(tarea.getId()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pr02findTareaByIdInexistenteTest() {
        assertNull(tareaService.findTareaById(Long.parseLong("0000")));
    }


    @Test
    public void pr03eliminarTareaTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2022-10-17");
            Jornada jornada = new Jornada(fecha, empleado, false);

            Tarea tarea = new Tarea();
            Set<Tarea> tareas = new HashSet<>();
            tareas.add(tarea);
            jornada.setTareas(tareas);

            jornadaService.addJornada(jornada);

            assertNotNull(tareaService.findTareaById(tarea.getId()));

            tareaService.eliminarTarea(tarea);

            assertNull(tareaService.findTareaById(tarea.getId()));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pr04getTareasSinTareasTest() {

        assertEquals(tareaService.getTareas().size(), 0);
    }

    @Test
    public void pr05getTareasTest() {

        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2022-10-17");
            Jornada jornada = new Jornada(fecha, empleado, false);

            Tarea tarea = new Tarea();
            Tarea tarea2 = new Tarea();
            Set<Tarea> tareas = new HashSet<>();
            tareas.add(tarea);
            tareas.add(tarea2);
            jornada.setTareas(tareas);

            jornadaService.addJornada(jornada);

            assertEquals(tareaService.getTareas().size(),2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
