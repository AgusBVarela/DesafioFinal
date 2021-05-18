# Bootcamp Mercado Libre - Desafio Final

* **Grupo:** 1
* **Wave:** 4
* **Autor:** Varela Agustina Belén
* **Fecha de Entrega:** 18/05/2021

## Api Documentation
### Usuarios 👩🏻‍💻🧑🏻‍💻
Para registrar un nuevo usuario se debe acceder al endpoint:
`POST` https:https://test_varela-agustina.furyapps.io/api/v1/sign-in  
Con el body:
* **username:** agustina
* **password:** 1234  

Esto retornara un token necesario para realizar las demas consultas que se debe ubicar en el header "Authorization".

### Requerimeintos 💡
#### ml-parts-01 - Consultar Repuestos
Devuelve la lista de los repuestos con sus datos técnicos, precios y descuentos aplicados.
* Parametros de entrada 
  
  | **Parametro**       | **Descripcion**                                                                                                                                                                                                                | **Obligatorio**          |
  |---------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |--------------------------|
  | username y password | Usuario y contraseña suministrado por la casa matriz (utilizarlo en la cabecera Access Token)                                                                                                                                  |            SI            |
  | queryType           | Tipo de Consulta (C: Completa, P: Parcial (solo repuestos que se modificaron desde la fecha de consulta a la actual) y V: Variación de precios (solo piezas que variaron de precio desde la fecha de consulta a la actual)     |            NO            |
  | date                | Fecha desde la cual se tienen en cuenta los cambios para consulta parcial o de variación de precios                                                                                                                            | Para querytype "P" y "V" |
  | order               | Para ordernar ascendentemente (1) o descendentemente (2)                                                                                                                                                                       |            NO            |

* Enpoints
  
  | HTTP  | URL                                                                                         | Descripcion                                                                                                                                                                                                             |
  |-------|---------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
  | `GET` | https://test_pigino-sofia.furyapps.io/api/v1/parts/list                                     | Ver una lista de repuestos completa. Si la lista no existe, debe devolver un “404 Not Found”.                                                                                                                           |
  | `GET` | https://test_pigino-sofia.furyapps.io/api/v1/parts/list?queryType=C                         | Ver una lista de repuestos según tipo de consulta. Si la lista no existe, debe devolver un “404 Not Found”.                                                                                                             |
  | `GET` | https://test_pigino-sofia.furyapps.io/api/v1/parts/list?queryType=P&date=2020-02-01         | Ver una lista de repuestos según tipo de consulta y fecha seleccionada. Si la lista no existe, debe devolver un “404 Not Found”.                                                                                        |
  | `GET` | https://test_pigino-sofia.furyapps.io/api/v1/parts/list?queryType=P&order=1&date=2020-02-01 | Ver una lista de repuestos según tipo de consulta y fecha seleccionada ordenada ascendentemente (1) y descendentemente (2) y por última fecha de variación (3) Si la lista no existe, debe devolver un “404 Not Found”. |

#### ml-parts-02 - Consultar pedidos de concesionario
Devuelve una lista de los pedidos de un concesionario que tiene la central del país que lo  solicita a la casa matriz de Brasil.
* Parametros de entrada
  
  | Parametro      | Descripcion                                                                                     | Obligatorio |
  |----------------|-------------------------------------------------------------------------------------------------|-------------|
  | dealerNumber   | Número del CE (Concesionario) que lo solicita                                                   | SI          |
  | deliveryStatus | Estado para los pedidos:   P: Pendientes de entrega, D: demorado, F: Finalizados, C: Cancelados | NO          |

* Enpoints
  
  | HTTP  | URL                                                                                               | Descripcion                                                                                                                                                                                                           |
  |-------|---------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
  | `GET` | https://test_pigino-sofia.furyapps.io/api/v1/parts/orders?dealerNumber=1&deliveryStatus=D         | Ver una lista de pedidos de un concesionario con el estado de pedido seleccionado. Si la lista no existe, debe devolver un “404 Not Found”.                                                                           |
  | `GET` | https://test_pigino-sofia.furyapps.io/api/v1/parts/orders?dealerNumber=1&deliveryStatus=D&order=1 | Ver una lista de pedidos de un concesionario con el estado de pedido seleccionado, ordenado por fecha del pedido ascendentemente (1) y descendentemente (2). Si la lista no existe, debe devolver un “404 Not Found”. |
  | `GET` | https://test_pigino-sofia.furyapps.io/api/v1/parts/orders?dealerNumber=1                          | Ver una lista de todos los pedidos de un concesionario. Si la lista no existe, debe devolver un “404 Not Found”.                                                                                                      |
  | `GET` | https://test_pigino-sofia.furyapps.io/api/v1/parts/orders?dealerNumber=1&order=1                  | Ver una lista de todos los pedidos de un concesionario, ordenada ascendentemente (1) y descendentemente (2). Si la lista no existe, debe devolver un “404 Not Found”.                                                 |

#### ml-parts-03 - Consultar estado de Pedido
Devuelve el estado de un pedido con una lista de los repuestos con estados individuales que  tiene la central del país que lo solicita con la casa matriz de Brasil
* Parametros de entrada
  
  | Parametro     | Descripcion                                  | Obligatorio |
  |---------------|----------------------------------------------|-------------|
  | orderNumberCM | Numero de Pedido generado por la casa Matriz | SI          |

* Enpoints
  
  | HTTP  | URL                                                                          | Descripcion                                                                                                                                     |
  |-------|------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|
  | `GET` | https://test_pigino-sofia.furyapps.io/api/v1/parts/orders/0001-0001-00000001 | Ver un pedido de repuestos según el número de orden de la casa matriz (orderNumberCM) Si el pedido no existe, debe devolver un “404 Not Found”. |

#### ml-parts-04 - Dar de alta un nuevo repuesto
La casa matriz en Brasil, cuenta con un depósito en donde se dan de alta las nuevas partes/repuestos que actualmente no se encuentran registradas. La finalidad de este requerimiento es permitir el alta de nuevos repuestos en conjunto con la cantidad de ellos que se ingresa al stock.
* Payload
```
{
    "partcode": 12330323,
    "widthdimension": 1,
    "talldimension": 1,
    "longdimension": 1,
    "netweight": 1,
    "provider": 1,
    "description": "espejos 321",
    "quantity": 200,
    "partprice": {
        "price":800.0,
        "urgent_price":1500,
        "sale_price":700
    }
}
```
* Enpoints
  
  | HTTP   | URL                                                | Descripcion                                                                                                                                                                                                                                                                                                      |
  |--------|----------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
  | `POST` | https://test_pigino-sofia.furyapps.io/api/v1/parts | Añadir un nuevo repuesto (aún no existente) al catálogo de partes/repuestos. Si se añade satisfactoriamente, devuelve “201 Created” con la referencia a la URI y el contenido de la lista. Si el repuesto ingresado ya existe, se actualiza el stock del repuesto ingresado aumentando con la cantidad recibida. |
  | `POST` | https://test_pigino-sofia.furyapps.io/api/v1/parts | Actualizar el stock de un repuesto existente. Si se actualiza correctamente, devolver el status code correspondiente, caso contrario informar dicha situación.                                                                                                                                                   |

#### ml-parts-05 - Enviar orden de repuestos
Se debe permitir a cada sucursal de cada país poder enviar su orden con el detalle y cantidades de los repuestos que necesita adquirir y recibir una respuesta a dicha solicitud.
Por otro lado, se debe permitir a la casa matriz actualizar los estados de las órdenes, obteniendo una respuesta a dicha solicitud.
* Payload  
Para dar de alta una orden: 
```
{
    "deliverydate" : "2020-03-09",
    "serialnumber" : "asdqw",
    "ordertype" : 1,
    "idshipping" : 1,
    "parts" : [
        {
            "idpart": 2,
            "idaccounttype": 1,
            "quantity": 1
        },
        {
            "idpart": 1,
            "idaccounttype": 2,
            "quantity": 1
        }
    ]
}
```
Para actualizar el estado de una orden:
```
{
    "idorden":3,
    "codestatus":"C"
}
```
* Enpoints
  
  | HTTP   | URL                                                                     | Descripcion                                                                                                                                                                                                                                                                                                                           |
  |--------|-------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
  | `POST` | https://test_pigino-sofia.furyapps.io/api/v1/parts/orders               | Permite dar de alta nuevas órdenes de repuestos por parte de cada sucursal. En caso de que la orden sea creada correctamente, se deberá devolver el correspondiente status code.                                                                                                                                                      |
  | `POST` | https://test_pigino-sofia.furyapps.io/api/v1/parts/orders/update_status | Permite actualizar el estado de cada una de las órdenes. A partir de la actualización del estado de una orden se deberán modificar las correspondientes cantidades en los stocks de las sucursales por país y de la casa matriz. En caso de que se haya actualizado correctamente, se deberá devolver el correspondiente status code. |

#### ml-parts-06 - Obtener todas las partes que tuvieron alguna orden previo o posterior a una fecha. Obtener las ofertas recomendadas.
Este requerimiento busca:
* En primera instancia permite obtener las distitnas partes que tuvieron alguna order previa a la fecha ingresada por parámetro.
* En primera instancia permite obtener las distitnas partes que tuvieron alguna order posterior o igual a la fecha ingresada por parámetro.
* ¿Hay algo mejor que poder saber la mercadería que no se está vendiendo para poder generar ofertas en los productos y conseguir las ventas? En este caso, te permite saber todas las partes que no fueron solicitadas a partir de la fecha ingresada por parámetro.


* Parametros de entrada /charges
  
  | Parametro | Descripcion                                                   | Obligatorio           |
  |-----------|---------------------------------------------------------------|-----------------------|
  | date      | Fecha con la que se desea tratar.                             | SI                    |
  | searchType| 1: Previo a la fecha. 2: Posterior o igual a la fecha.        | SI                    |

* Parametros de entrada /offer
  
  | Parametro | Descripcion                                                   | Obligatorio           |
  |-----------|---------------------------------------------------------------|-----------------------|
  | date      | Fecha con la que se desea tratar.                             | SI                    |
 
 
 
* Enpoints

| HTTP  | URL                                                                                      | Descripcion                                                                      |
|-------|------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| `GET` |https://test_varela-agustina.furyapps.io/api/v1/parts/charges?date=YYYY-MM-DD&searchType=1| Se obtienen las partes que poseen una o más ordenes previo a la fecha.           |
| `GET` |https://test_varela-agustina.furyapps.io/api/v1/parts/charges?date=YYYY-MM-DD&searchType=2| Se obtienen las partes que poseen una o más ordenes posterior o igual a la fecha.|
| `GET` |https://test_varela-agustina.furyapps.io/api/v1/parts/offer?date=YYYY-MM-DD               | Se obtienen todas las partes que NO poseen orden a partir de la fecha.           |

#### Coverage
Al hacer la exclusión de los siguientes paquetes que no deberían ser probados:
* **Clase**: .Application
* **Clase**: .CustomUserDetailsService
* **Paquete**: .security
* **Paquete**: .config
* **Paquete**: .dtos
* **Paquete**: .exceptions
* **Paquete**: .api
* **Paquete**: .model
* **Paquete**: .repository
* **Paquete**: .util
Como resultado final se obtuvo un coverage de 92%.

