package com.uniovi.integracion.pageobjects;

import com.uniovi.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PO_View {

    protected static int timeout = 3;

    public static int getTimeout() {
        return timeout;
    }

    public static void setTimeout(int timeout) {
        PO_View.timeout = timeout;
    }

    /**
     *  Espera por la visibilidad de un elemento/s en la vista actualmente cargandose en driver..
     *
     * @param driver: apuntando al navegador abierto actualmente.
     * @param type:
     * @param text:
     * @return Se retornará la lista de elementos resultantes de la búsqueda.
     */
    static public List<WebElement> checkElement(WebDriver driver, String type, String text) {
        List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, type, text, getTimeout());
        return elementos;
    }

    static public List<WebElement> comprobarElementos(WebDriver driver,  String text, int indice) {
        List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", text, getTimeout());
        elementos.get(indice).click();
        return elementos;
    }
}
