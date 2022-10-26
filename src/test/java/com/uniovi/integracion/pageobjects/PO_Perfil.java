package com.uniovi.integracion.pageobjects;

import com.uniovi.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Perfil extends PO_View {


    public static void verPerfil(WebDriver driver, String nombre, String apellido, String username, String rol, String diasLibres) {

        checkElement(driver, "text", nombre);
        checkElement(driver, "free", "//input[@id='apellidos']");
        checkElement(driver, "free", "//input[contains(text(), '"+apellido+"')]");
//        checkElement(driver, "text", apellido);
//        checkElement(driver, "text", username);
//        checkElement(driver, "text", rol);
//        checkElement(driver, "text", diasLibres);

    }

    public static void modificarPassword(WebDriver driver, String password) {

        driver.findElement(By.id("modificarPassword")).click();


        WebElement password1=driver.findElement(By.name("nuevaPassword"));
        password1.click();
        password1.clear();
        password1.sendKeys(password);

        WebElement password2=driver.findElement(By.name("repetirPassword"));
        password2.click();
        password2.clear();
        password2.sendKeys(password);

        driver.findElement(By.id("aceptarModificacion")).click();

        checkElement(driver, "text", "Contraseña modificada correctamente");


    }

    public static void comprobarPassword(WebDriver driver, String password) {

        driver.findElement(By.id("mostrarPassword")).click();
        checkElement(driver, "text", password);

    }
}
