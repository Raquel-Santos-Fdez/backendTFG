package com.uniovi.unit;

import com.uniovi.entities.Estacion;
import com.uniovi.services.EstacionService;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class EstacionTest {

    @Autowired
    private EstacionService estacionService;


    @Before
    public void antesDeCadaMetodo() {
        if (estacionService.getEstacionById("1")!=null)
            estacionService.deleteEstacion("1");
        if (estacionService.getEstacionById("2")!=null)
            estacionService.deleteEstacion("2");
    }

    @After
    public void despuesDeCadaMetodo() {
        if (estacionService.getEstacionById("1")!=null)
            estacionService.deleteEstacion("1");
        if (estacionService.getEstacionById("2")!=null)
            estacionService.deleteEstacion("2");
    }

    @Test
    public void pr01addEstacionTest() {
        int nEstaciones = estacionService.getEstaciones().size();
        Estacion estacion = new Estacion("1", "Ferreros", 1.1, 1.1);
        estacionService.addEstacion(estacion);
        assertEquals(estacionService.getEstaciones().size(), nEstaciones + 1);
    }

    @Test
    public void pr02addEstacionRepetidaTest() {
        int nEstaciones = estacionService.getEstaciones().size();
        Estacion estacion = new Estacion("1", "Ferreros", 1.1, 1.1);
        Estacion estacion2 = new Estacion("1", "Ferreros", 1.1, 1.1);
        estacionService.addEstacion(estacion);
        estacionService.addEstacion(estacion2);
        assertEquals(estacionService.getEstaciones().size(), nEstaciones+1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pr03addEstacionInvalidaTest() {
        Estacion estacion = new Estacion("", "", 1.1, 1.1);
        estacionService.addEstacion(estacion);
    }

    @Test
    public void pr04getEstacionByIdTest() {
        Estacion estacion = new Estacion("1", "Ferreros", 1.1, 1.1);
        estacionService.addEstacion(estacion);
        assertNotNull(estacionService.getEstacionById("1"));
    }

    @Test
    public void pr05deleteEstacionTest() {
        Estacion estacion = new Estacion("1", "Ferreros", 1.1, 1.1);
        estacionService.addEstacion(estacion);
        int nEstaciones = estacionService.getEstaciones().size();
        estacionService.deleteEstacion("1");
        assertEquals(estacionService.getEstaciones().size(), nEstaciones - 1);
    }

}
