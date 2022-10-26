package com.uniovi.unit;

import com.uniovi.entities.*;
import com.uniovi.services.*;
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
public class TareaStopTest {

    @Autowired
    private TareaStopsService tareaStopsService;

    @Autowired
    private EstacionService estacionService;

    @Before
    public void antesDeCadaTest(){
        tareaStopsService.eliminarTodos();
        if (estacionService.getEstacionById("1")!=null)
            estacionService.deleteEstacion("1");
    }

    @After
    public void despuesDeCadaTest(){
        tareaStopsService.eliminarTodos();
        if (estacionService.getEstacionById("1")!=null)
            estacionService.deleteEstacion("1");
    }

    @Test
    public void pr01addNuevaTareaStopTest() {
        Estacion estacion = new Estacion("1", "Ferreros", 1.1, 1.1);
        estacionService.addEstacion(estacion);
        Tarea tarea=new Tarea();
        Tarea_stops tarea_stops=new Tarea_stops(Tarea_stops.Situacion.INICIO, estacion, tarea );
        tareaStopsService.addNuevaTareaStop(tarea_stops);
        assertEquals(tareaStopsService.getAll().size(),1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pr02addNuevaTareaStopInvalidaTest() {
        tareaStopsService.addNuevaTareaStop(new Tarea_stops(null,null,null));
    }

}
