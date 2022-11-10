package com.uniovi.unit;

import com.uniovi.entities.*;
import com.uniovi.services.EmpleadoService;
import com.uniovi.services.JornadaService;
import com.uniovi.services.SolicitudService;
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
public class SolicitudTest {

    @Autowired
    private SolicitudService solicitudService;
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private JornadaService jornadaService;

    @Before
    public void antesDeCadaTest() {
        solicitudService.deleteAllSolicitudes();
        jornadaService.eliminarTodos();
        empleadoService.getEmpleados().forEach(e -> empleadoService.deleteEmpleado(e.getId()));
    }

    @After
    public void despuesDeCadaTest() {
        solicitudService.deleteAllSolicitudes();
        jornadaService.eliminarTodos();
        empleadoService.getEmpleados().forEach(e -> empleadoService.deleteEmpleado(e.getId()));
    }

    @Test
    public void pr01setSolicitudInvalidaTest() {
        solicitudService.setSolicitud(null);
        assertEquals(solicitudService.getAllSolicitudesPendientes().size(), 0);
    }

    @Test
    public void pr02setSolicitudTest() {
        Empleado empleado=new Empleado();
        empleadoService.addEmpleado(empleado);
        solicitudService.setSolicitud(new SolicitudSimple("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado));
        assertEquals(solicitudService.getAllSolicitudesPendientes().size(), 1);
    }

    @Test
    public void pr04rechazarSolicitudTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SolicitudSimple solicitudSimple = new SolicitudSimple("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado);
        solicitudService.setSolicitud(solicitudSimple);
        assertEquals(solicitudService.getAllSolicitudesPendientes().size(), 1);
        solicitudService.rechazarSolicitud(solicitudService.getAllSolicitudesPendientes().get(0).getId());
        assertEquals(solicitudService.getAllSolicitudesPendientes().size(), 0);
    }

    @Test
    public void pr05getAllSolicitudesPendientesSinSolicitudesTest() {
        assertEquals(solicitudService.getAllSolicitudesPendientes().size(), 0);
    }

    @Test
    public void pr06getAllSolicitudesPendientesVariasTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SolicitudSimple solicitudSimple = new SolicitudSimple("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado);
        SolicitudVacaciones solicitudVacaciones = new SolicitudVacaciones("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado);
        solicitudService.setSolicitud(solicitudSimple);
        solicitudService.solicitarVacaciones(solicitudVacaciones);
        assertEquals(solicitudService.getAllSolicitudesPendientes().size(), 2);
    }

    @Test
    public void pr07getAllSolicitudesPendientesConRechazadasTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SolicitudSimple solicitudSimple = new SolicitudSimple("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado);
        SolicitudVacaciones solicitudVacaciones = new SolicitudVacaciones("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado);
        solicitudService.setSolicitud(solicitudSimple);
        solicitudService.solicitarVacaciones(solicitudVacaciones);
        solicitudService.rechazarSolicitud(solicitudService.getAllSolicitudesPendientes().get(0).getId());
        assertEquals(solicitudService.getAllSolicitudesPendientes().size(), 1);
    }

    @Test
    public void pr08getAllSolicitudesPendientesIntercambioTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SolicitudIntercambio solicitudIntercambio = new SolicitudIntercambio();
        solicitudService.addSolicitudIntercambio(solicitudIntercambio);
        assertEquals(solicitudService.getAllSolicitudesPendientes().size(), 0);
    }

    @Test
    public void pr09addSolicitudIntercambioSimpleTest() {

        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SolicitudSimple solicitudSimple = new SolicitudSimple("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado);
        solicitudService.addSolicitudIntercambio(solicitudSimple);
        assertEquals(solicitudService.getAllSolicitudesPendientes().size(), 0);
        assertEquals(solicitudService.findOwnSolicitudes(empleado.getId()).size(), 0);
    }

    @Test
    public void pr10addSolicitudIntercambioInvalidaTest() {
        solicitudService.addSolicitudIntercambio(null);
        assertEquals(solicitudService.getAllSolicitudesPendientes().size(), 0);
    }

    @Test
    public void pr11addSolicitudIntercambioTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SolicitudIntercambio solicitudIntercambio = new SolicitudIntercambio("2022-10-17", Solicitud.MotivoAusencia.LICENCIA,
                empleado, "2022-11-17", null);
        solicitudService.addSolicitudIntercambio(solicitudIntercambio);
        assertEquals(solicitudService.findOwnSolicitudes(empleado.getId()).size(), 1);
    }

    @Test
    public void pr12solicitarVacacionesInvalidoTest() {
        solicitudService.solicitarVacaciones(null);
        assertEquals(solicitudService.getAllSolicitudesPendientes().size(), 0);
    }

    @Test
    public void pr13solicitarVacacionesTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SolicitudVacaciones solicitudVacaciones = new SolicitudVacaciones("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado);
        solicitudService.solicitarVacaciones(solicitudVacaciones);
        SolicitudVacaciones solicitudVacaciones2 = new SolicitudVacaciones("2022-11-17", Solicitud.MotivoAusencia.LICENCIA, empleado);
        solicitudService.solicitarVacaciones(solicitudVacaciones2);
        assertEquals(solicitudService.getAllSolicitudesPendientes().size(), 2);
    }

    @Test
    public void pr14findSolicitudesVacacionesNullIDTest() {
        assertEquals(solicitudService.findSolicitudesVacaciones(null).size(), 0);

    }

    @Test
    public void pr15findSolicitudesVacacionesInexistenteTest() {
        assertEquals(solicitudService.findSolicitudesVacaciones(Long.parseLong("00000")).size(), 0);

    }

    @Test
    public void pr16findSolicitudesVacacionesTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SolicitudVacaciones solicitudVacaciones = new SolicitudVacaciones("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado);
        solicitudService.solicitarVacaciones(solicitudVacaciones);
        SolicitudVacaciones solicitudVacaciones2 = new SolicitudVacaciones("2022-11-17", Solicitud.MotivoAusencia.LICENCIA, empleado);
        solicitudService.solicitarVacaciones(solicitudVacaciones2);
        assertEquals(solicitudService.findSolicitudesVacaciones(empleado.getId()).size(), 2);

    }

    @Test
    public void pr17findOwnSolicitudesTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        Empleado empleado2 = new Empleado();
        empleadoService.addEmpleado(empleado2);

        SolicitudIntercambio solicitudIntercambio = new SolicitudIntercambio("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado, "2022-11-17", null);
        solicitudService.addSolicitudIntercambio(solicitudIntercambio);

        SolicitudSimple solicitudSimple = new SolicitudSimple("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado);
        solicitudService.setSolicitud(solicitudSimple);

        SolicitudSimple solicitudSimple2 = new SolicitudSimple("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado2);
        solicitudService.setSolicitud(solicitudSimple2);

        assertEquals(solicitudService.findOwnSolicitudes(empleado.getId()).size(), 2);
    }

    @Test
    public void pr18findOwnSolicitudesNullTest() {
        assertEquals(solicitudService.findOwnSolicitudes(null).size(), 0);
    }

    @Test
    public void pr19findOwnSolicitudesInvalidTest() {
        assertEquals(solicitudService.findOwnSolicitudes(Long.parseLong("0000")).size(), 0);
    }

    @Test
    public void pr22findOthersSolicitudesPendientesInvalidTest() {
        assertEquals(solicitudService.findOthersSolicitudesPendientes(null).size(), 0);
    }

    @Test
    public void pr23findOthersSolicitudesPendientesFechasNoCoincidentesTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        Empleado empleado2 = new Empleado();
        empleadoService.addEmpleado(empleado2);

        SolicitudIntercambio solicitudIntercambio = new SolicitudIntercambio("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado2, "2022-11-17", null);
        solicitudService.addSolicitudIntercambio(solicitudIntercambio);

        assertEquals(solicitudService.findOthersSolicitudesPendientes(empleado).size(), 0);
    }

    @Test
    public void pr24findOthersSolicitudesPendientesTest() {
        Empleado empleado = new Empleado("empleado1", "Empleado1", "Prueba", "empleado1@gmail.com", "11111111A", "Password1", Empleado.Rol.MAQUINISTA, 100);
        empleadoService.addEmpleado(empleado);
        Empleado empleado2 = new Empleado("empleado2", "Empleado1", "Prueba", "empleado2@gmail.com", "22222222A", "Password1", Empleado.Rol.MAQUINISTA, 100);
        empleadoService.addEmpleado(empleado2);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2022-10-17");
            //trabaja en la fechaDescanso y descansa en fecha
            jornadaService.addJornada(new Jornada(fecha, empleado));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SolicitudIntercambio solicitudIntercambio2 = new SolicitudIntercambio("2022-11-17", Solicitud.MotivoAusencia.LICENCIA, empleado2, "2022-10-17", null);
        solicitudService.addSolicitudIntercambio(solicitudIntercambio2);

        assertEquals(solicitudService.findOthersSolicitudesPendientes(empleado).size(), 1);
    }


    //Tiene una jornada pero es diaLibre
    @Test
    public void pr25findOthersSolicitudesPendientesDiaLibreTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        Empleado empleado2 = new Empleado();
        empleadoService.addEmpleado(empleado2);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2022-10-17");
            //trabaja en la fechaDescanso y descansa en fecha
            jornadaService.addJornada(new Jornada(fecha, empleado, true));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SolicitudIntercambio solicitudIntercambio2 = new SolicitudIntercambio("2022-11-17", Solicitud.MotivoAusencia.LICENCIA, empleado2, "2022-10-17", null);
        solicitudService.addSolicitudIntercambio(solicitudIntercambio2);

        assertEquals(solicitudService.findOthersSolicitudesPendientes(empleado).size(), 0);
    }

    //Tiene jornada en ambas fechas
    @Test
    public void pr26findOthersSolicitudesPendientesOcupadoTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        Empleado empleado2 = new Empleado();
        empleadoService.addEmpleado(empleado2);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        Date fecha2 = null;
        try {
            fecha = sdf.parse("2022-10-17");
            fecha2 = sdf.parse("2022-11-17");
            //trabaja en la fechaDescanso y descansa en fecha
            jornadaService.addJornada(new Jornada(fecha, empleado, true));
            jornadaService.addJornada(new Jornada(fecha2, empleado, true));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SolicitudIntercambio solicitudIntercambio2 = new SolicitudIntercambio("2022-11-17", Solicitud.MotivoAusencia.LICENCIA, empleado2, "2022-10-17", null);
        solicitudService.addSolicitudIntercambio(solicitudIntercambio2);

        assertEquals(solicitudService.findOthersSolicitudesPendientes(empleado).size(), 0);
    }

    @Test
    public void pr27aceptarSolicitudIntercambioTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);
        SolicitudIntercambio solicitudIntercambio2 = new SolicitudIntercambio("2022-11-17", Solicitud.MotivoAusencia.LICENCIA, empleado, "2022-10-17", null);
        solicitudService.addSolicitudIntercambio(solicitudIntercambio2);

        solicitudService.aceptarSolicitud(solicitudIntercambio2);
        assertEquals(solicitudIntercambio2.getEstado(), Solicitud.EstadoSolicitud.PENDIENTE);
    }

    //la jornada para la fecha de la solicitud no existe
    @Test
    public void pr28aceptarSolicitudSimpleTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);

        SolicitudSimple solicitudSimple = new SolicitudSimple("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado);
        solicitudService.setSolicitud(solicitudSimple);

        solicitudSimple = (SolicitudSimple) solicitudService.getAllSolicitudesPendientes().get(0);
        solicitudService.aceptarSolicitud(solicitudSimple);

        assertEquals(solicitudSimple.getEstado(), Solicitud.EstadoSolicitud.ACEPTADA);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse("2022-10-17");
            assertTrue(jornadaService.findJornadaByFechaEmpleado(fecha, empleado.getId()).get(0).isDiaLibre());
            assertEquals(jornadaService.findJornadaByFechaEmpleado(fecha, empleado.getId()).get(0).getTareas().size(), 0);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //La jornada para la fecha de la solicitud existe y tiene tareas

    @Test
    public void pr29aceptarSolicitudSimpleJornadaTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);

        SolicitudSimple solicitudSimple = new SolicitudSimple("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado);
        solicitudService.setSolicitud(solicitudSimple);

        solicitudSimple = (SolicitudSimple) solicitudService.getAllSolicitudesPendientes().get(0);

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

            solicitudService.aceptarSolicitud(solicitudSimple);

            assertEquals(solicitudSimple.getEstado(), Solicitud.EstadoSolicitud.ACEPTADA);

            assertTrue(jornadaService.findJornadaByFechaEmpleado(fecha, empleado.getId()).get(0).isDiaLibre());
            assertEquals(jornadaService.findJornadaByFechaEmpleado(fecha, empleado.getId()).get(0).getTareas().size(), 0);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void pr30aceptarSolicitudVacacionesTest() {
        Empleado empleado = new Empleado();
        empleadoService.addEmpleado(empleado);

        int UN_DIA_EN_MILISEGUNDOS = 1000 * 60 * 60 * 24;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        Date fechaFinal = null;
        try {
            fecha = sdf.parse("2022-10-17");
            fechaFinal = sdf.parse("2022-10-20");

            SolicitudVacaciones solicitudVacaciones = new SolicitudVacaciones("2022-10-17", Solicitud.MotivoAusencia.LICENCIA, empleado, "2022-10-20");
            solicitudService.solicitarVacaciones(solicitudVacaciones);

            solicitudService.aceptarSolicitud(solicitudVacaciones);

            assertEquals(solicitudVacaciones.getEstado(), Solicitud.EstadoSolicitud.ACEPTADA);

            for (Date d = fecha; d.toInstant().isBefore(fechaFinal.toInstant()); d = new Date(d.getTime() + UN_DIA_EN_MILISEGUNDOS)) {
                assertTrue(jornadaService.findJornadaByFechaEmpleado(d, empleado.getId()).get(0).isDiaLibre());
                assertEquals(jornadaService.findJornadaByFechaEmpleado(d, empleado.getId()).get(0).getTareas().size(), 0);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
