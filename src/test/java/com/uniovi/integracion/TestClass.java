package com.uniovi.integracion;

import com.uniovi.integracion.pageobjects.PO_Home;
import com.uniovi.integracion.pageobjects.PO_Horarios;
import com.uniovi.integracion.pageobjects.PO_Login;
import com.uniovi.integracion.pageobjects.PO_View;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.uniovi.tests.util.SeleniumUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClass {

    static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";

    static String Geckdriver024 = "src/main/resources/test/geckodriver-v0.30.0-win64.exe";

    static WebDriver driver=getDriver(PathFirefox65, Geckdriver024);
    static String URL="http://localhost:4200";

    public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
        System.setProperty("webdriver.firefox.bin", PathFirefox);
        System.setProperty("webdriver.gecko.driver", Geckdriver);
        driver = new FirefoxDriver();
        return driver;
    }

    //Antes de cada prueba se navega al URL home de la aplicaciónn
    @Before
    public void setUp(){
        driver.navigate().to(URL);
    }

    //Después de cada prueba se borran las cookies del navegador
    @After
    public void tearDown(){
        driver.manage().deleteAllCookies();
    }

    //Antes de la primera prueba
    @BeforeClass
    static public void begin() {
    }

    //Al finalizar la última prueba
    @AfterClass
    static public void end() {
        //Cerramos el navegador al finalizar las pruebas
        driver.quit();
    }

    @Test
    public void pr01Login(){
        PO_Home.clickOption(driver, "/login", "class", "identificarse");
        PO_Login.fillForm(driver, "admin1", "Password1");
        PO_View.checkElement(driver, "id", "idSlider");
        PO_Home.logout(driver);
//        PO_Home.clickOption(driver, "/", "class", "logout");
    }

    @Test
    public void pr02LoginError(){
        PO_Home.clickOption(driver, "/login", "class", "identificarse");
        PO_Login.fillForm(driver, "a", "Password1");
        PO_Login.checkIncorrecto(driver);
    }

    @Test
    public void pr03ConsultarHorarios(){
        PO_Home.clickOption(driver,"/horarios", "id", "aHorarios" );
        PO_Horarios.seleccionarEstaciones(driver, "PUENTE DE LOS FIERROS", "POLA DE LENA");
        PO_View.checkElement(driver, "text", "HORARIO");
    }

}
