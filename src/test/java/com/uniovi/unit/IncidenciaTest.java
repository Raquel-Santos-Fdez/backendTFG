package com.uniovi.unit;

import com.uniovi.entities.Incidencia;
import com.uniovi.entities.Tren;
import com.uniovi.services.IncidenciaService;
import com.uniovi.services.TrenService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class IncidenciaTest {

    @Autowired
    private IncidenciaService incidenciaService;

    @Autowired
    private TrenService trenService;

    Tren tren = new Tren();

    @Before
    public void antesDeCadaTest() {
        trenService.addTren(tren);
        incidenciaService.getAllIncidencias().forEach(s -> incidenciaService.deleteIncidencia(s.getId()));
    }

    @After
    public void despuesDeCadaTest(){
        trenService.eliminarTodos();
        incidenciaService.getAllIncidencias().forEach(s -> incidenciaService.deleteIncidencia(s.getId()));
    }

    @Test
    public void pr01AddInicidenciaTest() {
        Incidencia incidencia = new Incidencia("Incidencia prueba", tren);
        incidenciaService.addInicidencia(incidencia);
        assertEquals(incidenciaService.getAllIncidencias().size(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pr02AddInicidenciaNoValida() {
        Incidencia incidencia = new Incidencia("", null);
        incidenciaService.addInicidencia(incidencia);
    }

    @Test
    public void pr03getIncidenciasTest() {
        Incidencia incidencia = new Incidencia("Incidencia prueba", tren);
        Incidencia incidencia2 = new Incidencia("Incidencia prueba 2", tren);
        incidenciaService.addInicidencia(incidencia);
        incidenciaService.addInicidencia(incidencia2);
        assertEquals(incidenciaService.getAllIncidencias().size(), 2);
    }

    @Test
    public void pr04getIncidenciasPendingTest() {

        Incidencia incidencia = new Incidencia("Incidencia prueba",tren);
        Incidencia incidencia2 = new Incidencia("Incidencia prueba 2", tren);
        incidenciaService.addInicidencia(incidencia);
        incidencia2.setEstado(Incidencia.EstadoIncidencia.RESUELTA);
        incidenciaService.addInicidencia(incidencia2);
        assertEquals(incidenciaService.getIncidenciasPending(tren.getId()).size(), 1);
    }

    @Test
    public void pr05eliminarIncidenciaExistenteTest() {
        Incidencia incidencia = new Incidencia("Incidencia prueba", tren);
        incidenciaService.addInicidencia(incidencia);
        incidenciaService.deleteIncidencia(incidencia.getId());
        assertEquals(incidenciaService.getAllIncidencias().size(), 0);

    }

    @Test(expected = Exception.class)
    public void pr06eliminarIncidenciaNoExistenteTest() {
        incidenciaService.deleteIncidencia(Long.parseLong("000000"));
        assertEquals(incidenciaService.getAllIncidencias().size(), 0);

    }
}
