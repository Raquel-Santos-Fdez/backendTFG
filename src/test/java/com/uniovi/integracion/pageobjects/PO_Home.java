package com.uniovi.integracion.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Home extends PO_NavView {

    public static void logout(WebDriver driver) {
        driver.findElement(By.className("logout")).click();
    }
}
