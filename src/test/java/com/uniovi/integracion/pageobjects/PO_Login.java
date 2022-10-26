package com.uniovi.integracion.pageobjects;

import com.uniovi.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PO_Login extends PO_Home{

    static public void fillForm(WebDriver driver, String usernamep, String passwordp){
        WebElement username=driver.findElement(By.name("username"));
        username.click();
        username.clear();
        username.sendKeys(usernamep);

        WebElement password=driver.findElement(By.name("password"));
        password.click();
        password.clear();
        password.sendKeys(passwordp);

        By boton=By.id("loginBtn");
        driver.findElement(boton).click();
    }

    public static void checkIncorrecto(WebDriver driver) {

        SeleniumUtils.EsperaCargaPagina(driver, "text", "Usuario o contraseña incorrectos",getTimeout());
    }
}
