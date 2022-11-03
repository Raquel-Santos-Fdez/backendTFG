package com.uniovi.unit;

import com.uniovi.entities.Tren;
import com.uniovi.services.TrenService;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrenTest {

    @Autowired
    private TrenService trenService;

    @Before
    public void antesDeCadaTest() {
        trenService.deleteAll();
    }

    @After
    public void despuesDeCadaTest() {
        trenService.deleteAll();
    }

    @Test
    public void pr01addTrenTest() {
        Tren tren = new Tren();
        trenService.addTren(tren);
        assertNotNull(trenService.findTrenById(tren.getId()));
    }

    @Test
    public void pr02findTrenByIdI() {
        assertNull(trenService.findTrenById(Long.parseLong("0000")));
    }


}
