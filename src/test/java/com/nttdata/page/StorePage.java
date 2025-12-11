package com.nttdata.page;

import org.openqa.selenium.By;

public class StorePage {

    public static By categoryClothes = By.cssSelector("#category-3 > a");
    public static By subMen = By.cssSelector("#category-4 > a");
    public static By categoriaInexistente = By.cssSelector("#category-30 > a");
    public static By productoLista = By.cssSelector("#js-product-list > div.products.row > div");
    public static By cantidadProducto = By.id("quantity_wanted");
    public static By btnAgregarCarrito = By.cssSelector("#add-to-cart-or-refresh > div.product-add-to-cart.js-product-add-to-cart > div > div.add > button");
    public static By popupConfirmacion = By.cssSelector("#blockcart-modal > div > div > div.modal-body");
    public static By btnCarrito = By.cssSelector("#_mobile_cart > div");
    public static By tituloCarrito = By.cssSelector("#main > div > div.cart-grid-body.col-lg-8 > div > div.card-block > h1");
}
