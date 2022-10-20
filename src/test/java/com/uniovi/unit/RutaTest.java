package com.uniovi.unit;

import com.uniovi.entities.Estacion;
import com.uniovi.entities.Route_stop;
import com.uniovi.entities.Ruta;
import com.uniovi.services.EstacionService;
import com.uniovi.services.RutaService;
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
public class RutaTest {

    @Autowired
    private RutaService rutaService;

    @Autowired
    private EstacionService estacionService;

    @Before
    public void antesDeCadaTest() {
        rutaService.eliminarRutaStops();
        if (rutaService.getRutaById("1") != null)
            rutaService.eliminarRuta(rutaService.getRutaById("1"));
    }

    @After
    public void despuessDeCadaTest() {
        rutaService.eliminarRutaStops();
        if (rutaService.getRutaById("1") != null)
            rutaService.eliminarRuta(rutaService.getRutaById("1"));
    }

    @Test
    public void pr01AddRutaValidaTest() {
        int nRutas = rutaService.getRutas().size();
        Ruta ruta = new Ruta("1", "p", "prueba");
        rutaService.addRuta(ruta);
        assertEquals(rutaService.getRutas().size(), nRutas + 1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void pr02AddRutaInvalidaTest() {
        Ruta ruta = new Ruta("", "", "");
        rutaService.addRuta(ruta);
    }

    @Test
    public void pr03AddRutaRepetidaTest() {
        int nRutas = rutaService.getRutas().size();
        Ruta ruta = new Ruta("1", "p", "prueba");
        Ruta ruta2 = new Ruta("1", "p", "prueba");
        rutaService.addRuta(ruta);
        rutaService.addRuta(ruta2);
        assertEquals(rutaService.getRutas().size(), nRutas + 1);
    }

    @Test
    public void pr04getRutaByIdValidaTest() {
        Ruta ruta = new Ruta("1", "p", "prueba");
        rutaService.addRuta(ruta);
        assertNotNull(rutaService.getRutaById("1"));
    }

    @Test
    public void pr05getRutaByIdNoExistenteTest() {
        assertNull(rutaService.getRutaById("0000"));
    }

    @Test
    public void pr06findRutaByEstacionesInexistentesTest() {
        assertEquals(rutaService.findRutaByEstaciones("0000", "0001").size(), 0);
    }

    @Test
    public void pr07findRutaByEstacionesTest() {
        Estacion estacion1 = new Estacion("1", "Ferreros", 1.1, 1.1);
        Estacion estacion2 = new Estacion("2", "Bueño", 1.1, 1.1);

        estacionService.addEstacion(estacion1);
        estacionService.addEstacion(estacion2);

        Ruta ruta=new Ruta("1", "p", "prueba");
        rutaService.addRuta(ruta);

        Route_stop ruta_stop=new Route_stop(1, estacion1, ruta);
        Route_stop ruta_stop2=new Route_stop(2, estacion2, ruta);

        rutaService.addRutaStop(ruta_stop);
        rutaService.addRutaStop(ruta_stop2);

        assertTrue(rutaService.findRutaByEstaciones(estacion1.getId(), estacion2.getId()).size()>0);
    }

    @Test
    public void pr08findRutaByEstacionesSinRutaTest() {

        Estacion estacion1 = new Estacion("1", "Ferreros", 1.1, 1.1);
        Estacion estacion2 = new Estacion("2", "Bueño", 1.1, 1.1);

        estacionService.addEstacion(estacion1);
        estacionService.addEstacion(estacion2);

        assertEquals(rutaService.findRutaByEstaciones("16403", "15410").size(), 0);

    }


}
