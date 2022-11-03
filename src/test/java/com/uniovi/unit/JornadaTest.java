package com.uniovi.unit;

import com.uniovi.entities.*;
import com.uniovi.services.EmpleadoService;
import com.uniovi.services.JornadaService;
import com.uniovi.services.SolicitudService;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class JornadaTest {

    @Autowired
    private JornadaService jornadaService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private TareaService tareaService;

    @Autowired
    private SolicitudService solicitudService;

    @Before
    public void antesDeCadaMetodo() {
        jornadaService.eliminarTodos();
        tareaService.getTareas().forEach(t-> tareaService.eliminarTarea(t));
        solicitudService.deleteAllSolicitudes();
        empleadoService.getEmpleados().forEach(e -> empleadoService.deleteEmpleado(e.getId()));
    }

    @After
    public void despuesDeCadaMetodo() {
        jornadaService.eliminarTodos();
        tareaService.getTareas().forEach(t-> tareaService.eliminarTarea(t));
        solicitudService.deleteAllSolicitudes();
        empleadoService.getEmpleados().forEach(e -> empleadoService.deleteEmpleado(e.getId()));
    }

    @Test
    public void pr01AddJornadaTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2022-10-17");
            Jornada jornada = new Jornada(fecha, empleado, false);
            jornadaService.addJornada(jornada);
            assertEquals(jornadaService.findJornadaByFechaEmpleado(fecha, empleado.getId()).size(), 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void pr02AddJornadaNoValida() {
        Jornada jornada = new Jornada(null, null, false);
        jornadaService.addJornada(jornada);
    }

    @Test
    public void pr03findJornadaByDateTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        Empleado empleado2 = new Empleado();
        empleadoService.addEmpleado(empleado2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2020-10-17");
            Jornada jornada = new Jornada(fecha, empleado, false);
            Jornada jornada2 = new Jornada(fecha, empleado2, false);
            jornadaService.addJornada(jornada);
            jornadaService.addJornada(jornada2);
            assertEquals(jornadaService.findJornadaByFecha(fecha).size(), 2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void pr04findJornadaByDateInexistenteTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2022-10-17");
            assertEquals(jornadaService.findJornadaByFecha(fecha).size(), 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pr05findJornadaByEmpleadoTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        Date fecha2 = null;
        try {
            fecha = sdf.parse("2022-10-17");
            fecha2 = sdf.parse("2022-09-17");
            Jornada jornada = new Jornada(fecha, empleado, false);
            Jornada jornada2 = new Jornada(fecha2, empleado, false);
            jornadaService.addJornada(jornada);
            jornadaService.addJornada(jornada2);
            assertEquals(jornadaService.findJornadaByEmpleado(empleado.getId()).size(), 2);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void pr06findJornadaByEmpleadoInexistenteTest() {
        assertEquals(jornadaService.findJornadaByEmpleado(Long.parseLong("0000000")).size(), 0);
    }

    @Test
    public void pr07findJornadaByDateEmpleadoTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2022-10-17");
            Jornada jornada = new Jornada(fecha, empleado, false);
            jornadaService.addJornada(jornada);
            assertEquals(jornadaService.findJornadaByFechaEmpleado(fecha, empleado.getId()).size(), 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pr08marcarDiaLibreJornadaExistenteTest() {
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

            assertEquals(jornadaService.getTareasByFechaEmpleado(empleado.getId(), fecha).size(), 1);
            jornadaService.marcarDiaLibre(fecha, empleado);
            assertEquals(jornadaService.getTareasByFechaEmpleado(empleado.getId(), fecha).size(), 0);
            assertTrue(jornadaService.findJornadaByFechaEmpleado(fecha, empleado.getId()).get(0).isDiaLibre());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void pr09marcarDiaLibreJornadaInexistenteTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2022-01-17");
            assertEquals(jornadaService.findJornadaByFechaEmpleado(fecha, empleado.getId()).size(), 0);
            jornadaService.marcarDiaLibre(fecha, empleado);
            assertEquals(jornadaService.findJornadaByFechaEmpleado(fecha, empleado.getId()).size(), 1);
            assertTrue(jornadaService.findJornadaByFechaEmpleado(fecha, empleado.getId()).get(0).isDiaLibre());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pr10getTareasByFechaEmpleadoInexistenteTest(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2022-01-17");
            assertEquals(jornadaService.getTareasByFechaEmpleado(Long.parseLong("0000"),fecha ).size(), 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void pr11getTareasByFechaEmpleadoTest(){
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

            assertEquals(jornadaService.getTareasByFechaEmpleado(empleado.getId(), fecha).size(), 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pr12getTareasByFechaEmpleadoSinTareasTest(){
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2022-10-17");
            Jornada jornada = new Jornada(fecha, empleado, false);

            jornadaService.addJornada(jornada);

            assertEquals(jornadaService.getTareasByFechaEmpleado(empleado.getId(), fecha).size(), 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pr13getTareasByFechaEmpleadoInvalidoTest(){
        assertEquals(jornadaService.getTareasByFechaEmpleado(null, null).size(), 0);
    }

    @Test
    public void pr14getTareasByFechaEmpleadoSinJornadaTest(){
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2022-10-17");

            assertEquals(jornadaService.getTareasByFechaEmpleado(empleado.getId(), fecha).size(), 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pr15getTareasByFechaEmpleadoOtroEmpleadoTest(){
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        Empleado empleado2=new Empleado();
        empleadoService.addEmpleado(empleado2);
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

            assertEquals(jornadaService.getTareasByFechaEmpleado(empleado2.getId(), fecha).size(), 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pr16getTareasByFechaEmpleadoOtraFechaTest(){
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        Date fecha2=null;
        try {
            fecha = sdf.parse("2022-10-17");
            fecha2 = sdf.parse("2020-10-17");
            Jornada jornada = new Jornada(fecha, empleado, false);

            Tarea tarea = new Tarea();
            Set<Tarea> tareas = new HashSet<>();
            tareas.add(tarea);
            jornada.setTareas(tareas);

            jornadaService.addJornada(jornada);

            assertEquals(jornadaService.getTareasByFechaEmpleado(empleado.getId(), fecha2).size(), 0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //No existe jornada
    //No existe ninguna tarea
    //Datos invalidos
    //Existe y es correcta
    //El empleado no existe
    //Existen tareas para ese empleado pero en otra fecha
    //Existen tareas para esa fecha pero otro empleado


    //El empleado que la solicita no tiene una jornada ese dia
    @Test
    public void pr17reasignarSinJornadaTest(){
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        Empleado empleado2=new Empleado();
        empleadoService.addEmpleado(empleado2);
        SolicitudIntercambio solicitudIntercambio=new SolicitudIntercambio(
                "2022-10-17", "Licencia",empleado, "2022-11-17",empleado2);
        solicitudService.addSolicitudIntercambio(solicitudIntercambio);

        jornadaService.reasignar(solicitudIntercambio);

        assertEquals(solicitudIntercambio.getEstado(), Solicitud.EstadoSolicitud.PENDIENTE);
    }

    //Se solicita reasignar con el nuevo empleado null
    @Test
    public void pr18reasignarNuevoNullTest(){
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        Empleado empleado2=new Empleado();
        empleadoService.addEmpleado(empleado2);
        SolicitudIntercambio solicitudIntercambio=new SolicitudIntercambio(
                "2022-10-17", "Licencia",empleado, "2022-11-17",null);
        solicitudService.addSolicitudIntercambio(solicitudIntercambio);

        jornadaService.reasignar(solicitudIntercambio);

        assertEquals(solicitudIntercambio.getEstado(), Solicitud.EstadoSolicitud.PENDIENTE);
    }

    @Test
    public void pr19reasignarTest(){
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        Empleado empleado2=new Empleado();
        empleadoService.addEmpleado(empleado2);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        Date fecha2=null;
        try {
            fecha = sdf.parse("2022-10-17");
            fecha2=sdf.parse("2022-11-17");
            Jornada jornada = new Jornada(fecha, empleado, false);
            Jornada jornada2 = new Jornada(fecha2, empleado2, false);

            jornadaService.addJornada(jornada);
            jornadaService.addJornada(jornada2);

            SolicitudIntercambio solicitudIntercambio=new SolicitudIntercambio(
                    "2022-10-17", "Detraibe",empleado, "2022-11-17",empleado2);
            solicitudService.addSolicitudIntercambio(solicitudIntercambio);

            assertEquals(jornadaService.findJornadaByFechaEmpleado(fecha, empleado2.getId()).size(),0);
            solicitudIntercambio=(SolicitudIntercambio) solicitudService.findOwnSolicitudes(empleado.getId()).get(0);
            jornadaService.reasignar(solicitudIntercambio);

            assertEquals(jornadaService.findJornadaByFechaEmpleado(fecha, empleado2.getId()).size(),1);
            assertEquals(solicitudIntercambio.getEstado(), Solicitud.EstadoSolicitud.REASIGNADA);

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }



}
