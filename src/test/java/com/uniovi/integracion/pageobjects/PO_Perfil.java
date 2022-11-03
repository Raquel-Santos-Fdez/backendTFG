package com.uniovi.integracion.pageobjects;

import com.uniovi.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PO_Perfil extends PO_View {


    public static void verPerfil(WebDriver driver, String nombre, String apellido, String username, String rol, String diasLibres) {

        SeleniumUtils.checkInput(driver, "nombre", nombre);
        SeleniumUtils.checkInput(driver, "apellidos", apellido);
        SeleniumUtils.checkInput(driver, "username", username);
        SeleniumUtils.checkInput(driver, "role", rol);
        SeleniumUtils.checkInput(driver, "diasLibres", diasLibres);

    }

    public static void modificarPassword(WebDriver driver, String password1p, String password2p) {

        driver.findElement(By.id("modificarPassword")).click();


        WebElement password1 = driver.findElement(By.name("nuevaPassword"));
        password1.click();
        password1.clear();
        password1.sendKeys(password1p);

        WebElement password2 = driver.findElement(By.name("repetirPassword"));
        password2.click();
        password2.clear();
        password2.sendKeys(password2p);

        driver.findElement(By.id("aceptarModificacion")).click();

        checkElement(driver, "text", "Contraseña modificada correctamente");


    }

    public static void comprobarPassword(WebDriver driver, String password) {

        driver.findElement(By.id("mostrarPassword")).click();
        SeleniumUtils.checkInput(driver, "password", password);

    }
}
