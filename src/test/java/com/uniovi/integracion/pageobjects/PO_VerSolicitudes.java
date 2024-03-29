package com.uniovi.integracion.pageobjects;

import com.uniovi.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Calendar;
import java.util.Locale;

public class PO_VerSolicitudes extends PO_View {
    public static void aceptarSolicitud(WebDriver driver, String fecha, String empleado) {

        comprobarElementos(driver, "//td[contains(text(), '" + fecha + "')]" +
                "/following-sibling::*/button[@id='aceptarSolAdminBtn']", 0);

    }

    public static void rechazarSolicitud(WebDriver driver, String fecha, String empleado) {
        comprobarElementos(driver, "//td[contains(text(), '" + fecha + "')]" +
                "/following-sibling::*/button[@id='rechazarSolAdminBtn']", 0);
    }

    public static void checkNoSolicitud(WebDriver driver, String fecha, String empleado) {
        SeleniumUtils.elementoNoPresentePagina(driver, "//td[contains(text(), '" + fecha + "')]" +
                "/following-sibling::td[.//button[contains(text(),'" + empleado + "')]]" +
                "/following-sibling::*/button[@id='aceptarSolAdminBtn']", PO_View.getTimeout());
    }

    public static void aceptarVacaciones(WebDriver driver) {
        driver.findElement(By.id("aceptarVacaciones")).click();
    }

    public static void comprobarSolicitud(WebDriver driver, String fecha, String motivo, String empleado) {
        String textoBoton=driver.findElement(By.xpath("//td[contains(text(), '"+fecha+"')]" +
                "/following-sibling::td[contains(text(),'" + motivo + "')]"
                +"/following-sibling::*/button[@id='btnDetallesEmpleadoSol']")).getText();

        SeleniumUtils.checkTexto(textoBoton, empleado);
    }
}
