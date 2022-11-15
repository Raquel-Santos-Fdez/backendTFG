package com.uniovi.integracion.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PO_SolicitarVacaciones {
    public static void solicitarVacaciones(WebDriver driver, int i) {

        driver.findElement(By.id("solVacacionesBtn")).click();

        PO_View.comprobarElementos(driver, "//tr[@id='filaVacaciones']["+i+"]/td[2]",0);

        driver.findElement(By.id("solicitarV")).click();
    }

    public static void solicitarVacacionesRepetida(WebDriver driver, int i) {

        driver.findElement(By.id("solVacacionesBtn")).click();

        PO_View.checkElement(driver, "text", "Las solicitudes de vacaciones existentes");



    }
}
