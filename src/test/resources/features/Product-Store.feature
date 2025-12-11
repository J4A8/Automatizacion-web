# language: es
@testfeature
Característica: Product - Store
  Yo, como usuario
  Quiero, poder comprar productos
  Para validar precios y carrito correctamente

  @test
  Escenario: Validación del precio de un producto
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "<usuario>" y clave "<clave>"
    Cuando navego a la categoría "<categoria>" y subcategoría "<subcategoria>"
    Y agrego 2 unidades del primer producto al carrito
    Entonces valido en el popup la confirmación del producto agregado
    Y valido en el popup que el monto total sea calculado correctamente
    Cuando finalizo la compra
    Entonces valido el titulo de la pagina del carrito
    Y vuelvo a validar el calculo de precios en el carrito

    Ejemplos:
      |usuario                |clave      |categoria |subcategoria|
      |meci300828@gmail.com   |Cixe#3008  |Clothes   |Men         |
      |edu@gmail.com          |1111111111  |Clothes   |Men         |
      |meci300828@gmail.com   |Cixe#3008  |Autos     |Men         |
