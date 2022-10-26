package com.uniovi.integracion.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PO_VerSolicitudes extends PO_View {
    public static void aceptarSolicitudSimple(WebDriver driver) {

//        comprobarElementos(driver, "//td[contains(text(), '"+empleado+"')]/following-sibling::*/button[@id='eliminarEmpleadoBtn']",0);

        driver.findElement(By.id("aceptarSolAdminBtn")).click();

    }
}
