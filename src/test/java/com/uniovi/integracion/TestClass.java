package com.uniovi.integracion;

import com.uniovi.integracion.pageobjects.*;
import com.uniovi.utils.SeleniumUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Calendar;
import java.util.Locale;

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

    @Test
    public void pr04ConsultarHorariosSinRuta(){
        PO_Home.clickOption(driver,"/horarios", "id", "aHorarios" );
        PO_Horarios.seleccionarEstaciones(driver, "GIJON", "AVILES");
        driver.switchTo().alert().accept();
    }

    @Test
    public void pr05addEmpleado(){
        PO_Home.clickOption(driver, "/login", "class", "identificarse");
        PO_Login.fillForm(driver, "admin1", "Password1");

        By boton=By.id("gestUJBtn");
        driver.findElement(boton).click();

        PO_GestUsuarioJornada.addUsuario(driver, "empleado2", "Empleado2", "Prueba",
                "33333333C", "empleado2@gmail.com", "REVISOR");

        PO_View.checkElement(driver, "text", "empleado2");
    }

    @Test
    public void pr06eliminarEmpleado(){

        By boton=By.id("gestUJBtn");
        driver.findElement(boton).click();

        PO_GestUsuarioJornada.eliminarEmpleado(driver, "empleado2");

        SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "empleado2", PO_View.getTimeout());
    }

    @Test
    public void pr07detallesEmpleado(){

        By boton=By.id("gestUJBtn");
        driver.findElement(boton).click();

        PO_GestUsuarioJornada.addUsuario(driver, "empleado2", "Empleado2", "Prueba",
                "33333333C", "empleado2@gmail.com", "REVISOR");
        PO_GestUsuarioJornada.verDetallesEmpleado(driver, "empleado2");
        PO_View.checkElement(driver, "text", "Detalles");
    }

    @Test
    public void pr08addTarea(){

        By boton=By.id("gestUJBtn");
        driver.findElement(boton).click();

        PO_GestUsuarioJornada.addTarea(driver, "October 5, 2022", "empleado1", "1", "tarea prueba integracion 5oct",
                "PUENTE DE LOS FIERROS", "POLA DE LENA", "10:00", "12:00", "1");

        PO_View.checkElement(driver, "text", "tarea prueba integracion 5oct");

    }

    @Test
    public void pr09addTareaSinEmpleado() {

        By boton=By.id("gestUJBtn");
        driver.findElement(boton).click();

        driver.findElement(By.cssSelector("button[aria-label='October 25, 2022']")).click();

        driver.findElement(By.id("btnNuevaTarea")).click();

        PO_View.checkElement(driver, "text", "Selecciona un día y un empleado");
    }

    @Test
    public void pr10verDetallesTarea(){

        By boton=By.id("gestUJBtn");
        driver.findElement(boton).click();

        PO_GestUsuarioJornada.verDetallesTarea(driver, "empleado2", "tarea prueba integracion 5oct");

        PO_View.checkElement(driver, "text", "Detalles de la tarea");

        driver.findElement(By.id("cerrarDetallesJBtn")).click();


    }

    @Test
    public void pr11consultarJornada(){
        PO_Home.logout(driver);
        PO_Home.clickOption(driver, "/login", "class", "identificarse");
        PO_Login.fillForm(driver, "empleado1", "Password5");

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        PO_ConsultarJornada.consultarJornada(driver, "October 22, 2022");

        PO_ConsultarJornada.verDetallesTarea(driver, "tarea 22oct");

        PO_View.checkElement(driver, "text", "Consultar jornada");
    }

    @Test
    public void pr12addIncidencia(){

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        PO_ConsultarJornada.consultarJornada(driver, "October 22, 2022");

        PO_ConsultarJornada.verDetallesTarea(driver, "tarea 22oct");

        PO_ConsultarJornada.addIncidencia(driver, "Prueba intedgracion add incidencia");
    }

    @Test
    public void pr13solicitarDiaLibre(){

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        Calendar c=Calendar.getInstance();
        Locale locale = new Locale("en", "USA");
        String monthName=c.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);

        PO_ConsultarJornada.consultarJornada(driver, monthName+" 30, 2022");

        PO_ConsultarJornada.solicitarDiaLibre(driver, "10:30", "12:30", "Otro motivo");

        PO_View.checkElement(driver, "text", "DIA LIBRE");
    }

    @Test
    public void pr14solicitarVacaciones(){

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        PO_SolicitarVacaciones.solicitarVacaciones(driver, 3);

        PO_View.checkElement(driver, "text", "existente");
    }

    @Test
    public void pr15solicitarVacacionesRepetidas(){

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        PO_SolicitarVacaciones.solicitarVacacionesRepetida(driver);
    }

    @Test
    public void pr16addSolicitudIntercambio(){

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        driver.findElement(By.id("portalSolBtn")).click();

        PO_PortalSolicitudes.addSolicitud(driver, "10/22/2022", "12/25/2022", "Otro motivo");

        PO_View.checkElement(driver, "text", "La solicitud ha sido añadida correctamente");

    }

    @Test
    public void pr17addSolicitudIntercambioSinJornada(){

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        driver.findElement(By.id("portalSolBtn")).click();

        PO_PortalSolicitudes.addSolicitud(driver, "12/20/2022", "12/25/2022", "Otro motivo");

        PO_View.checkElement(driver, "text", "Debe seleccionar un intercambio para un día con una jornada asignada");

    }

    @Test
    public void pr18consultarMisSolicitudes(){


        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        driver.findElement(By.id("portalSolBtn")).click();

        PO_PortalSolicitudes.consultarMisSolicitudes(driver, "2022-10-22", "2022-12-25", "PENDIENTE");

    }

    @Test
    public void pr19consultarVacaciones(){

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        driver.findElement(By.id("portalSolBtn")).click();

        PO_PortalSolicitudes.consultarVacaciones(driver, "2022-12-01 -- 2022-12-20", "2022-06-16 -- 2022-06-30", "PENDIENTE");

    }

    @Test
    public void pr20verPerfil(){
        PO_Home.clickOption(driver, "/login", "class", "identificarse");
        PO_Login.fillForm(driver, "empleado1", "Password5");

        PO_Home.clickOption(driver, "/ver-perfil", "class", "verPerfil");

        PO_Perfil.verPerfil(driver,"Empleado1", "Prueba", "empleado1", "MAQUINISTA", "100");
    }

    @Test
    public void pr21modificarPassword(){

        PO_Home.clickOption(driver, "/ver-perfil", "class", "verPerfil");

        PO_Perfil.modificarPassword(driver, "Password1");
        PO_Perfil.comprobarPassword(driver, "Password1");

        PO_Home.logout(driver);
    }

    @Test
    public void pr22modificarPasswordNoCoincidente(){
        //TODO

    }

    @Test
    public void pr23verSolicitudes(){
        //TODO
    }

    @Test
    public void pr24aceptarSolicitudSimple(){
        PO_Home.clickOption(driver, "/login", "class", "identificarse");
        PO_Login.fillForm(driver, "admin1", "Password1");

        By menu=By.id("verSolBtn");
        driver.findElement(menu).click();

        PO_VerSolicitudes.aceptarSolicitudSimple(driver);


    }

    @Test
    public void pr25rechazarSolicitudSimple(){
        //TODO
    }

    @Test
    public void pr26aceptarSolicitudVacaciones(){
        //TODO
    }

    @Test
    public void pr27aceptarSolicitudIntercambio(){
        //TODO
    }

    @Test
    public void pr28filtarEmpleados(){
        //TODO
    }


}
