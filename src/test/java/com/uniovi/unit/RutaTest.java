package com.uniovi.unit;

import com.uniovi.entities.Estacion;
import com.uniovi.entities.Ruta_estacion;
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

import java.util.List;

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
        rutaService.eliminarRutaEstaciones();
        if (rutaService.getRutaById("1") != null)
            rutaService.eliminarRuta(rutaService.getRutaById("1"));
        estacionService.deleteEstacion("1");
        estacionService.deleteEstacion("2");
    }

    @After
    public void despuesDeCadaTest() {
        rutaService.eliminarRutaEstaciones();
        if (rutaService.getRutaById("1") != null)
            rutaService.eliminarRuta(rutaService.getRutaById("1"));
        estacionService.deleteEstacion("1");
        estacionService.deleteEstacion("2");
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

        Ruta_estacion ruta_stop=new Ruta_estacion(1, estacion1, ruta);
        Ruta_estacion ruta_stop2=new Ruta_estacion(2, estacion2, ruta);

        rutaService.addRutaEstacion(ruta_stop);
        rutaService.addRutaEstacion(ruta_stop2);

        List<Ruta_estacion> rs=rutaService.findRutaByEstaciones(estacion1.getId(), estacion2.getId());

        assertTrue(rs.size()>0);
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
