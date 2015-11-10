# Medidas importantes #
Todos los datos aca mencionados asumen que el tablero de pong es vertical, es decir ancho<alto. Las proporciones se sacaron usando como referencia [este tablero de pong](http://www.xnet.se/javaTest/jPong/jPong.html).

La proporción entre el alto y el ancho de un tablero de pong proporcionado puede ser 13/10. Es decir, el cociente alto/ancho es aprox. 1.3, en otros casos donde se manejaban tableros calculados a ojo se llego a una proporcion 11/10 (cociente 1.1) lo que nos deja asumir un tamaño para el tablero en general y podria hacer que el tablero se autoredimensione según una de las dos medidas.

Se cumple que 3.5 barras (su ancho) es aproximadamente el ancho total del tablero. La altura de la barra puede ser arbitraria. Luego barra=ancho/3.5

En el ancho de una barra pueden caber unas 4.5 bolas. Como dato curioso, en el ejemplo mostrado anteriormente, la velocidad de la bola aumenta si choca contra una pared, y se mantiene constante si tan solo choca contra las barras.

Se creo un archivo png con las dos barras y la bola y se indican las medidas, se espera opinion sobre si estas son las medidaes adecuadas para el diseño. Ver archivo en Downloads.

## Física ##


Dato mencionado por JP: "La velocidad de la bola se incrementa cada vez q rebota en la pared"