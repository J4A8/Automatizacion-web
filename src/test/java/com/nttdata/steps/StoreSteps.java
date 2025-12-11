package com.nttdata.steps;

import com.nttdata.page.StorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class StoreSteps {

    private WebDriver driver;
    private WebDriverWait wait;

    public StoreSteps(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void seleccionarCategoria(String categoria){
        if(categoria.equals("CLOTHES")) {
            driver.findElement(StorePage.categoryClothes).click();
            wait.until(ExpectedConditions.urlToBe("https://qalab.bensg.com/store/pe/3-clothes"));
        } else {
            driver.findElement(StorePage.categoriaInexistente).click();
        }
    }

    public void seleccionarSubcategoria(String subcategoria){
        if(subcategoria.equals("MEN")) {
            driver.findElement(StorePage.subMen).click();
            wait.until(ExpectedConditions.urlToBe("https://qalab.bensg.com/store/pe/4-men"));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(StorePage.productoLista));
        }
    }

    public void seleccionarPrimerProducto(){
        driver.findElements(StorePage.productoLista).get(0).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.cantidadProducto));
        wait.until(ExpectedConditions.elementToBeClickable(StorePage.btnAgregarCarrito));
    }

    public void agregarCantidad(int cantidad){
        WebElement qty = driver.findElement(StorePage.cantidadProducto);
    }

    public void agregarAlCarrito(){
        driver.findElement(StorePage.btnAgregarCarrito).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.popupConfirmacion));
    }

    public boolean popupConfirmacionVisible(){
        return driver.findElement(StorePage.popupConfirmacion).isDisplayed();
    }

    public boolean validarMontoPopup() {
        try {
            WebElement cantidadElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("#blockcart-modal > div > div > div.modal-body > div > div.col-md-5.divide-right > div > div:nth-child(2) > span.product-quantity")
            ));

            WebElement precioElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("#blockcart-modal > div > div > div.modal-body > div > div.col-md-5.divide-right > div > div:nth-child(2) > p")
            ));

            WebElement subtotalElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("#blockcart-modal > div > div > div.modal-body > div > div.col-md-7 > div > p:nth-child(2)")
            ));

            int cantidad = Integer.parseInt(cantidadElemento.getText().replaceAll("[^0-9]", "").trim());
            double precio = Double.parseDouble(precioElemento.getText().replace("S/", "").replaceAll("[^0-9.,]", "").trim().replace(",", "."));
            double subtotalCalculado = cantidad * precio;
            double subtotalPopup = Double.parseDouble(subtotalElemento.getText().replace("S/", "").replaceAll("[^0-9.,]", "").trim().replace(",", "."));

            return Math.abs(subtotalCalculado - subtotalPopup) < 0.01;
        } catch (Exception e) {
            return false;
        }
    }



    public void finalizarCompra(){
        driver.findElement(By.cssSelector("#blockcart-modal > div > div > div.modal-body > div > div.col-md-7 > div > div > a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.tituloCarrito));
    }

    public void abrirCarrito(){
        driver.findElement(StorePage.btnCarrito).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(StorePage.tituloCarrito));
    }

    public boolean validarTituloCarrito(){
        return driver.findElement(StorePage.tituloCarrito).isDisplayed();
    }

    public boolean validarMontoCarrito(){
        WebElement total = driver.findElement(By.cssSelector(
                "#main > div > div.cart-grid-right.col-lg-4 > div.card.cart-summary > div.cart-detailed-totals.js-cart-detailed-totals > div.card-block.cart-detailed-subtotals.js-cart-detailed-subtotals"
        ));
        return total.isDisplayed() && !total.getText().isEmpty();
    }
}
