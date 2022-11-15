package com.uniovi.integracion.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_GestUsuarioJornada extends PO_View{

    public static void addUsuario(WebDriver driver, String usernamep, String nombrep, String apellidop,
                                  String dnip, String emailp, String rol) {

        By botonAdd=By.id("addUsuario");
        driver.findElement(botonAdd).click();

        WebElement modalContainer = driver.findElement(
                By.className("nuevo-usuario"));

        WebElement username=modalContainer.findElement(By.name("username"));
        username.click();
        username.clear();
        username.sendKeys(usernamep);

        WebElement nombre=modalContainer.findElement(By.name("nombre"));
        nombre.click();
        nombre.clear();
        nombre.sendKeys(nombrep);

        WebElement apellido=modalContainer.findElement(By.name("apellido"));
        apellido.click();
        apellido.clear();
        apellido.sendKeys(apellidop);

        WebElement dni=modalContainer.findElement(By.name("dni"));
        dni.click();
        dni.clear();
        dni.sendKeys(dnip);

        WebElement email=modalContainer.findElement(By.name("email"));
        email.click();
        email.clear();
        email.sendKeys(emailp);

        driver.findElement(By.name("rolEmpleado")).click();
        List<WebElement> options=modalContainer.findElements(By.xpath("//mat-option[@id='roles']"));

        for(WebElement option: options) {
            if (option.getText().contains(rol)) {
                option.click();
                break;
            }
        }

        By boton=By.id("addUsuarioBtn");
        driver.findElement(boton).click();
    }

    public static void eliminarEmpleado(WebDriver driver, String empleado) {
        PO_View.comprobarElementos(driver, "//td[contains(text(), '"+empleado+"')]/following-sibling::*/button[@id='eliminarEmpleadoBtn']",0);
    }

    public static void verDetallesEmpleado(WebDriver driver, String empleado) {
        PO_View.comprobarElementos(driver, "//td[contains(text(), '"+empleado+"')]/following-sibling::*/button[@id='detallesEmpleadoBtn']",0);
    }

    public static void addTarea(WebDriver driver, String fecha, String empleado, String andenp, String descripcionp,
                                String origenp, String destinop, String hIniciop, String hFinp, String idTren) {

        driver.findElement(By.cssSelector("button[aria-label='"+fecha+"']")).click();
        PO_View.comprobarElementos(driver, "//td[contains(text(), '"+empleado+"')]",0);

        driver.findElement(By.id("btnNuevaTarea")).click();

        WebElement dialog = driver.findElement(
                By.className("nueva-tarea"));

        WebElement anden=dialog.findElement(By.name("anden"));
        anden.click();
        anden.clear();
        anden.sendKeys(andenp);

        WebElement descripcion=dialog.findElement(By.name("descripcion"));
        descripcion.click();
        descripcion.clear();
        descripcion.sendKeys(descripcionp);

        driver.findElement(By.name("selectOrigen")).click();
        List<WebElement> options=dialog.findElements(By.xpath("//mat-option[@id='origen']"));

        for(WebElement option: options) {
            if (option.getText().contains(origenp)) {
                option.click();
                break;
            }
        }

        driver.findElement(By.name("selectDestino")).click();
        List<WebElement> destinos=dialog.findElements(By.xpath("//mat-option[@id='destino']"));

        for(WebElement option: destinos) {
            if (option.getText().contains(destinop)) {
                option.click();
                break;
            }
        }

        WebElement horaInicio=dialog.findElement(By.name("horaInicio"));
        horaInicio.click();
        horaInicio.clear();
        horaInicio.sendKeys(hIniciop);

        WebElement horaFin=dialog.findElement(By.name("horaFin"));
        horaFin.click();
        horaFin.clear();
        horaFin.sendKeys(hFinp);

        driver.findElement(By.name("selectTrenes")).click();
        List<WebElement> trenes=dialog.findElements(By.xpath("//mat-option[@id='tren']"));

        for(WebElement option: trenes) {
            if (option.getText().contains(idTren)) {
                option.click();
                break;
            }
        }

        driver.findElement(By.id("addTarea")).click();
    }

    public static void verDetallesTarea(WebDriver driver, String empleado,  String descripcion) {
        PO_View.comprobarElementos(driver, "//td[contains(text(), '"+empleado+"')]",0);
        PO_View.comprobarElementos(driver, "//td[contains(text(), '"+descripcion+"')]/following-sibling::*/button[@id='detallesTarea']",0);


    }

    public static void filtrarEmpleado(WebDriver driver, String empleadop) {
        WebElement empleado=driver.findElement(By.name("filtroUsuarios"));
        empleado.click();
        empleado.clear();
        empleado.sendKeys(empleadop);


    }
}
