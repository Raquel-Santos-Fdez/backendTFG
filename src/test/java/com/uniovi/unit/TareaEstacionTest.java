package com.uniovi.unit;

import com.uniovi.entities.Estacion;
import com.uniovi.entities.Tarea;
import com.uniovi.entities.Tarea_estacion;
import com.uniovi.services.EstacionService;
import com.uniovi.services.TareaEstacionService;
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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TareaEstacionTest {

    @Autowired
    private TareaEstacionService tareaEstacionService;

    @Autowired
    private EstacionService estacionService;

    @Before
    public void antesDeCadaTest(){
        tareaEstacionService.eliminarTodos();
        if (estacionService.getEstacionById("1")!=null)
            estacionService.deleteEstacion("1");
    }

    @After
    public void despuesDeCadaTest(){
        tareaEstacionService.eliminarTodos();
        if (estacionService.getEstacionById("1")!=null)
            estacionService.deleteEstacion("1");
    }

    @Test
    public void pr01addNuevaTareaStopTest() {
        Estacion estacion = new Estacion("1", "Ferreros", 1.1, 1.1);
        estacionService.addEstacion(estacion);
        Tarea tarea=new Tarea();
        Tarea_estacion tarea_estacion =new Tarea_estacion(Tarea_estacion.Situacion.INICIO, estacion, tarea );
        tareaEstacionService.addNuevaTareaEstacion(tarea_estacion);
        assertEquals(tareaEstacionService.getAll().size(),1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void pr02addNuevaTareaStopInvalidaTest() {
        tareaEstacionService.addNuevaTareaEstacion(new Tarea_estacion(null,null,null));
    }

}
