package com.uniovi.integracion.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_Horarios {

    public static void seleccionarEstaciones(WebDriver driver, String origen, String destino) {
        driver.findElement(By.name("origen")).click();
        List<WebElement> options=driver.findElements(By.xpath("//mat-option[@id='optionsOrigen']"));
        for(WebElement option: options) {
            if (option.getText().contains(origen)) {
                option.click();
                break;
            }
        }

        driver.findElement(By.name("destino")).click();
        options=driver.findElements(By.xpath("//mat-option[@id='optionsDestino']"));
        for(WebElement option: options) {
            if (option.getText().contains(destino)) {
                option.click();
                break;
            }
        }

        By boton=By.id("consultarHorariosBtn");
        driver.findElement(boton).click();
    }
}
