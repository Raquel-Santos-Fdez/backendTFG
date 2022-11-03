package com.uniovi.integracion.pageobjects;

import com.uniovi.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PO_VerSolicitudes extends PO_View {
    public static void aceptarSolicitud(WebDriver driver, String fecha, String empleado) {

        comprobarElementos(driver, "//td[contains(text(), '" + fecha + "')]" +
                "/following-sibling::*[contains(text(),'" + empleado + "')]" +
                "/following-sibling::*[@id='aceptarSolAdminBtn']", 0);

    }

    public static void rechazarSolicitud(WebDriver driver, String fecha, String empleado) {
        comprobarElementos(driver, "//td[contains(text(), '" + fecha + "')]" +
                "/following-sibling::*[contains(text(),'" + empleado + "')]" +
                "/following-sibling::*[@id='rechazarSolAdminBtn']", 0);
    }

    public static void checkNoSolicitud(WebDriver driver, String fecha, String empleado) {
        SeleniumUtils.elementoNoPresentePagina(driver, "//td[contains(text(), '" + fecha + "')]" +
                "/following-sibling::*[contains(text(),'" + empleado + "')]" +
                "/following-sibling::*[@id='aceptarSolAdminBtn']", PO_View.getTimeout());
    }

    public static void aceptarVacaciones(WebDriver driver) {
//        driver.findElement(By.className("vacacionesDialog"));
        driver.findElement(By.id("aceptarVacaciones")).click();
    }
}
