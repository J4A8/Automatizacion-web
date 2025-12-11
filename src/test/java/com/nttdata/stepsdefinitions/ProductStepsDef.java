package com.nttdata.stepsdefinitions;

import com.nttdata.steps.LoginSteps;
import com.nttdata.steps.StoreSteps;
import io.cucumber.java.es.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;

public class ProductStepsDef {

    private WebDriver driver;

    @Dado("estoy en la página de la tienda")
    public void estoy_en_la_página_de_la_tienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe");
        screenShot();
    }

    @Y("me logueo con mi usuario {string} y clave {string}")
    public void me_logueo_con_mi_usuario_y_clave(String usuario, String clave) {
        driver.get("https://qalab.bensg.com/store/pe/iniciar-sesion?back=my-account");
        screenShot();
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.ingresarUsuario(usuario);
        loginSteps.ingresarClave(clave);
        loginSteps.login();
        screenShot();

        String currentUrl = driver.getCurrentUrl();
        if(currentUrl.contains("iniciar-sesion")) {
            throw new AssertionError("Inicio de sesión fallido con usuario: " + usuario);
        }
    }

    @Cuando("navego a la categoría {string} y subcategoría {string}")
    public void navego_a_la_categoría_y_subcategoría(String categoria, String subcategoria) {
        StoreSteps storeSteps = new StoreSteps(driver);
        storeSteps.seleccionarCategoria(categoria.toUpperCase());
        storeSteps.seleccionarSubcategoria(subcategoria.toUpperCase());
        screenShot();
    }

    @Y("agrego 2 unidades del primer producto al carrito")
    public void agrego_2_unidades_del_primer_producto_al_carrito() {
        StoreSteps storeSteps = new StoreSteps(driver);
        storeSteps.seleccionarPrimerProducto();
        storeSteps.agregarCantidad(2);
        storeSteps.agregarAlCarrito();
        screenShot();
    }

    @Entonces("valido en el popup la confirmación del producto agregado")
    public void valido_en_el_popup_la_confirmación_del_producto_agregado() {
        StoreSteps storeSteps = new StoreSteps(driver);
        Assertions.assertTrue(storeSteps.popupConfirmacionVisible());
        screenShot();
    }

    @Y("valido en el popup que el monto total sea calculado correctamente")
    public void valido_en_el_popup_que_el_monto_total_sea_calculado_correctamente() {
        StoreSteps storeSteps = new StoreSteps(driver);
        Assertions.assertTrue(storeSteps.validarMontoPopup());
        storeSteps.finalizarCompra();
        screenShot();
    }

    @Cuando("finalizo la compra")
    public void finalizo_la_compra() {
    }

    @Entonces("valido el titulo de la pagina del carrito")
    public void valido_el_titulo_de_la_pagina_del_carrito() {
        StoreSteps storeSteps = new StoreSteps(driver);
        Assertions.assertTrue(storeSteps.validarTituloCarrito());
        screenShot();
    }

    @Y("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvo_a_validar_el_calculo_de_precios_en_el_carrito() {
        StoreSteps storeSteps = new StoreSteps(driver);
        Assertions.assertTrue(storeSteps.validarMontoCarrito());
        screenShot();
    }
}
