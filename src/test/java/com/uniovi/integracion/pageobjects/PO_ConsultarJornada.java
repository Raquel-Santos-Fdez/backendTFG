package com.uniovi.integracion.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_ConsultarJornada {
    public static void consultarJornada(WebDriver driver, String fecha) {

        By consultarBtn=By.id("consultarJornadaBtn");
        driver.findElement(consultarBtn).click();

        driver.findElement(By.cssSelector("button[aria-label='"+fecha+"']")).click();
    }

    public static void verDetallesTarea(WebDriver driver, String descripcion) {
        PO_View.comprobarElementos(driver, "//td[contains(text(), '"+descripcion+"')]/following-sibling::*/button[@id='verDetallesBtn']",0);

    }

    public static void solicitarDiaLibre(WebDriver driver, String hIniciop, String hFinalp, String motivo) {
        driver.findElement(By.id("diaLibrePanel")).click();

        WebElement horaInicio=driver.findElement(By.name("horaInicio"));
        horaInicio.click();
        horaInicio.clear();
        horaInicio.sendKeys(hIniciop);

        WebElement horaFin=driver.findElement(By.name("horaFin"));
        horaFin.click();
        horaFin.clear();
        horaFin.sendKeys(hFinalp);

        driver.findElement(By.id("selectMotivo")).click();
        List<WebElement> options=driver.findElements(By.xpath("//mat-option[@class='motivo']"));

        for(WebElement option: options) {
            if (option.getText().contains(motivo)) {
                option.click();
                break;
            }
        }

        driver.findElement(By.id("btnSelMotivo")).click();
    }

    public static void addIncidencia(WebDriver driver, String incidenciap) {

        WebElement incidencia=driver.findElement(By.name("nuevaIncidencia"));
        incidencia.click();
        incidencia.clear();
        incidencia.sendKeys(incidenciap);

        driver.findElement(By.id("addIncidencia")).click();
    }
}
