package com.uniovi.integracion.pageobjects;

import com.uniovi.utils.SeleniumUtils;
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

        driver.findElement(By.name("selectMotivo")).click();
        List<WebElement> options=driver.findElements(By.xpath("//mat-option[@id='motivo']"));

        for(WebElement option: options) {
            if (option.getText().contains(motivo)) {
                option.click();
                break;
            }
        }

        driver.findElement(By.id("btnSelMotivo")).click();
    }

    public static void addIncidencia(WebDriver driver, String incidenciap) {

        WebElement modalContainer = driver.findElement(
                By.className("dialog-detalles-jornada"));

        WebElement incidencia=modalContainer.findElement(By.id("nuevasIncidencias"));
        incidencia.click();
        incidencia.clear();
        incidencia.sendKeys(incidenciap);

        modalContainer.findElement(By.id("addIncidencia")).click();
    }
}
