package com.uniovi.integracion;

import com.uniovi.entities.Empleado;
import com.uniovi.integracion.pageobjects.*;
import com.uniovi.services.EmpleadoService;
import com.uniovi.utils.SeleniumUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;

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
                "33333333C", "empleado2@gmail.com", "MAQUINISTA");

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
                "33333333C", "empleado2@gmail.com", "MAQUINISTA");
        PO_GestUsuarioJornada.verDetallesEmpleado(driver, "empleado2");
        PO_View.checkElement(driver, "text", "Detalles");
    }

    @Test
    public void pr08addTarea(){

        By boton=By.id("gestUJBtn");
        driver.findElement(boton).click();

        PO_GestUsuarioJornada.addTarea(driver, "November 30, 2022", "empleado1", "1", "tarea prueba integracion 30nov",
                "PUENTE DE LOS FIERROS", "POLA DE LENA", "10:00", "12:00", "1");

        PO_View.checkElement(driver, "text", "Tarea añadida correctamente");

    }

    @Test
    public void pr09addTareaSinEmpleado() {

        By boton=By.id("gestUJBtn");
        driver.findElement(boton).click();

        driver.findElement(By.cssSelector("button[aria-label='November 30, 2022']")).click();

        driver.findElement(By.id("btnNuevaTarea")).click();

        PO_View.checkElement(driver, "text", "Selecciona un día y un empleado");
    }

    @Test
    public void pr10verDetallesTarea(){

        By boton=By.id("gestUJBtn");
        driver.findElement(boton).click();

        PO_GestUsuarioJornada.verDetallesTarea(driver, "empleado1", "tarea prueba integracion 30nov");

        PO_View.checkElement(driver, "text", "Detalles de la tarea");

        driver.findElement(By.id("cerrarDetallesJBtn")).click();


    }

    @Test
    public void pr11consultarJornada(){
        PO_Home.logout(driver);
        PO_Home.clickOption(driver, "/login", "class", "identificarse");
        PO_Login.fillForm(driver, "empleado1", "Password1");

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        PO_ConsultarJornada.consultarJornada(driver, "November 30, 2022");

        PO_ConsultarJornada.verDetallesTarea(driver, "tarea prueba integracion 30nov");

        PO_View.checkElement(driver, "text", "Consultar jornada");
    }

    @Test
    public void pr12addIncidencia(){

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        PO_ConsultarJornada.consultarJornada(driver, "November 30, 2022");

        PO_ConsultarJornada.verDetallesTarea(driver, "tarea prueba integracion 30nov");

        SeleniumUtils.esperarSegundos(driver, 1);

        PO_ConsultarJornada.addIncidencia(driver, "Prueba integracion add incidencia");
    }

    @Test
    public void pr13solicitarDiaLibre(){
        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        Calendar c=Calendar.getInstance();
        Locale locale = new Locale("en", "USA");
        String monthName=c.getDisplayName(Calendar.MONTH, Calendar.LONG, locale);

        PO_ConsultarJornada.consultarJornada(driver, monthName+" 29, 2022");

        PO_ConsultarJornada.solicitarDiaLibre(driver, "10:30", "12:30", "OTRO_MOTIVO");

        PO_View.checkElement(driver, "text", "La solicitud ha sido enviada correctamente");
    }

    @Test
    public void pr14solicitarVacaciones(){

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        PO_SolicitarVacaciones.solicitarVacaciones(driver, 3);

        PO_View.checkElement(driver, "text", "existente");
    }

    @Test
    public void pr15volverASolicitarVacaciones(){

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();


        PO_SolicitarVacaciones.solicitarVacacionesRepetida(driver,3);
    }

    @Test
    public void pr16addSolicitudIntercambio(){

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        driver.findElement(By.id("portalSolBtn")).click();

        PO_PortalSolicitudes.addSolicitud(driver, "11/30/2022", "12/25/2022", "OTRO_MOTIVO");

        PO_View.checkElement(driver, "text", "La solicitud ha sido añadida correctamente");

    }

    @Test
    public void pr17addSolicitudIntercambioSinJornada(){

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        driver.findElement(By.id("portalSolBtn")).click();

        PO_PortalSolicitudes.addSolicitud(driver, "12/20/2022", "12/25/2022", "OTRO_MOTIVO");

        PO_View.checkElement(driver, "text", "Debe seleccionar un intercambio para un día con una jornada asignada");

    }

    @Test
    public void pr18consultarMisSolicitudes(){

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        driver.findElement(By.id("portalSolBtn")).click();

        PO_PortalSolicitudes.consultarMisSolicitudes(driver, "2022-11-30", "2022-12-25", "PENDIENTE");

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

        PO_Home.clickOption(driver, "/ver-perfil", "class", "verPerfil");

        PO_Perfil.verPerfil(driver,"Empleado1", "Prueba", "empleado1", "MAQUINISTA", "100");
    }

    @Test
    public void pr21modificarPassword(){

        PO_Home.clickOption(driver, "/ver-perfil", "class", "verPerfil");

        PO_Perfil.modificarPassword(driver, "Password5", "Password5");
        PO_Perfil.comprobarPassword(driver, "Password5");

        PO_View.checkElement(driver, "text", "Contraseña modificada correctamente");
    }

    @Test
    public void pr22modificarPasswordNoCoincidente(){

        PO_Home.clickOption(driver, "/ver-perfil", "class", "verPerfil");

        PO_Perfil.modificarPassword(driver, "Password1", "Password5");

        PO_View.checkElement(driver, "text", "Las contraseñas no coinciden");

    }

    @Test
    public void pr23verSolicitudes(){
        PO_Home.logout(driver);
        PO_Home.clickOption(driver, "/login", "class", "identificarse");
        PO_Login.fillForm(driver, "admin1", "Password1");

        By menu=By.id("verSolBtn");
        driver.findElement(menu).click();

        PO_VerSolicitudes.comprobarSolicitud(driver, "2022-11-29","OTRO_MOTIVO", "empleado1");
    }



    @Test
    public void pr24rechazarSolicitudSimple(){

        By menu=By.id("verSolBtn");
        driver.findElement(menu).click();

        PO_VerSolicitudes.rechazarSolicitud(driver,"2022-11-29", "empleado1");
        PO_VerSolicitudes.checkNoSolicitud(driver, "2022-11-29", "empleado1");
    }

    @Test
    public void pr25aceptarSolicitudSimple(){

        PO_Home.logout(driver);
        PO_Home.clickOption(driver, "/login", "class", "identificarse");
        PO_Login.fillForm(driver, "empleado1", "Password5");

        pr13solicitarDiaLibre();

        PO_Home.logout(driver);
        PO_Home.clickOption(driver, "/login", "class", "identificarse");
        PO_Login.fillForm(driver, "admin1", "Password1");

        By menu=By.id("verSolBtn");
        driver.findElement(menu).click();

        PO_VerSolicitudes.aceptarSolicitud(driver, "2022-11-29", "empleado1");

        PO_VerSolicitudes.checkNoSolicitud(driver, "2022-11-29", "empleado1");
    }

    @Test
    public void pr26aceptarSolicitudVacaciones(){

        By menu=By.id("verSolBtn");
        driver.findElement(menu).click();

        PO_VerSolicitudes.aceptarSolicitud(driver, "2022-12-01 - 2022-12-20", "empleado1");
        PO_VerSolicitudes.aceptarVacaciones(driver );

        PO_VerSolicitudes.checkNoSolicitud(driver, "2022-12-01 - 2022-12-20", "empleado1");
    }

    @Test
    public void pr27filtarEmpleados(){

        By boton=By.id("gestUJBtn");
        driver.findElement(boton).click();

        PO_GestUsuarioJornada.filtrarEmpleado(driver, "empleado1");

        SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "empleado2", PO_View.getTimeout());
    }

    @Test
    public void pr28aceptarSolicitudIntercambio(){

        By boton=By.id("gestUJBtn");
        driver.findElement(boton).click();

        driver.findElement(By.cssSelector("button[aria-label='Next month']")).click();

        PO_GestUsuarioJornada.addTarea(driver, "December 25, 2022", "empleado2", "1", "tarea prueba integracion 25dic",
                "PUENTE DE LOS FIERROS", "POLA DE LENA", "10:00", "12:00", "1");

        PO_Home.logout(driver);

        PO_Home.clickOption(driver, "/login", "class", "identificarse");
        PO_Login.fillForm(driver, "empleado2", "9EWLB9M6H4W");

        By menu=By.id("menuGestionBtn");
        driver.findElement(menu).click();

        driver.findElement(By.id("portalSolBtn")).click();

        PO_PortalSolicitudes.aceptarSolicitudIntercambio(driver, "2022-11-30", "2022-12-25", "empleado1");

        PO_View.checkElement(driver, "text", "La solicitud ha sido aceptada");

    }




}
