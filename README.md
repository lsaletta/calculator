# Calculadora Spring-Boot
Este proyecto implementa una POC de una Calculadora como micro-servicio (API) utilizando Spring-boot y Maven con funcionalidades básicas de sumar y restar.
Leer el [enunciado](https://github.com/lsaletta/calculator/blob/master/enunciado.md) para más información.

## Como utilizar
Abrir una consola y ejecutar:
```
git clone https://github.com/lsaletta/calculator
cd calculator
mvn validate
mvn clean install
java -jar target/calculator-0.0.1-SNAPSHOT.jar
```

## USO DE LA API
Despues de seguir los pasos anteriores, deberias de tener la API arrancada en: `http://localhost:8080/calculator`

Se ha añadido al micro-servicio Swagger por lo que la API tambien deberia ser accesible desde: http://localhost:8080/swagger-ui.html#/calculator-controller

El modelo definido para interactuar con la calculadora es el siguiente:
{
  "calculations": [
    { 
      "parameters": [
             X, Y, ...
           ],
      "operation_type": "TIPO"
    }
  ]
}

Disponemos de una lista de objectos "calculations", cada uno de estos elementos nos permite realizar un tipo de operacion, actualmente suma o resta.

Dentro del objeto calculations tenemos:
- parameters: Lista de valores con los que se quiere operar
- operation_type: Enumeracion que nos permitira seleccionar el tipo de operacion suma [SUM] o resta [SUBTRACT]

Un ejemplo de peticion seria el siguiente:

`POST: http://localhost:8080/calculator
Content-Type: application/json
BODY: {
          "calculations": [
              {
                  "parameters": [
                      28,
                      9
                  ],
                  "operation_type": "SUBTRACT"
              }
          ]
      }`

El resultado sera la operacion: 28 - 9 = 19

Resultado: `19.0`


####IMPORTANTE: 
En la primera version de la API solo sera necesario utilizar el primer elemento de la lista de "calculations", ya que la logica actual solo retorna el valor de ese primer calculo, 
pero en futuras versiones podremos hacer uso de la lista de "calculations" para poder llevar a cabo operaciones mas complejas como: sumas, restas, multiplicaciones y divisiones etc.. 
todas ellas desde la misma peticion.

Se ha optado por crear desde la primera version de la POC el objecto "calculations" como una lista, ya que para escalar la logica en versiones seria obligatorio que este campo fuera una lista 
y en caso de afrontar ese cambio a posteriori, estariamos "rompiendo el contrato" con los consumidores de esta API. Para poder soportar, desde la primera version, operaciones 
con mas de dos elementos, el objeto "parameters" se ha definido como lista. Adicionalmente, para poder realizar operaciones mas complejas, el modelo de la API seria escalable con solo añadir 
un nuevo campo, aunque todo dependera de la complejida de la logica que se quiera realizar.

