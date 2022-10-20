package com.uniovi.unit;

import com.uniovi.entities.Empleado;
import com.uniovi.entities.Jornada;
import com.uniovi.entities.Tarea;
import com.uniovi.entities.Tarea_stops;
import com.uniovi.services.EmpleadoService;
import com.uniovi.services.JornadaService;
import com.uniovi.services.TareaService;
import com.uniovi.services.TareaStopsService;
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

    @Before
    public void antesDeCadaTest(){
        tareaStopsService.eliminarTodos();
    }

    @After
    public void despuesDeCadaTest(){
        tareaStopsService.eliminarTodos();
    }

    @Test
    public void pr01addNuevaTareaStopTest() {
        tareaStopsService.addNuevaTareaStop(new Tarea_stops());
        assertEquals(tareaStopsService.getAll().size(),1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pr02addNuevaTareaStopInvalidaTest() {
        tareaStopsService.addNuevaTareaStop(new Tarea_stops(null,null,null));
    }

}
