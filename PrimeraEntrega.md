# PRIMERA ENTREGA EXPOANDES (05/03/2007) #


**OJO:Antes de editar por favor revisen PrimeraEntregaDocumentoBase**


## Objetivos y razón de ser del proyecto ##

El equipo de trabajo de **[greenPong ](.md)** pretende hacer posible que se juegue un clon del videojuego Pong, que fue de los primeros de la historia (ver, Pong[linea](en.md) <>), recibiendo las órdenes del usuario para mover la barra que le corresponde en el juego usando un sistema no convencional. Para este proyeto en específico el sistema no convencional se refiere a una pelota de tennis con un mango cuya posición en el espacio es identificada por una cámara conectada a un computador. La cámara unida a un programa llamado VTS (visual tracknig system) genera constantemente información que representa la posición de la bola y que el juego Pong usa para mover la barra del usuario hasta que pierde.

Es un proyecto que requiere un gran esfuerzo por parte de los integrantes del proyecto, conocimientos de programación medianamente avanzados y una considerable disponibilidad de tiempo. La creación de **[greenPong ](.md)** (Pong + VTS) es un intento por crear formas económicas y poco convencionales de interactividad. Existen dispositivos que hacen posible que los movimientos del usuario en el espacio real se reflejen en el espacio virtual, sin embargo, **[greenPong ](.md)** apela a la necesidad de conseguir dicha interactividad a bajo precio, así sea sólo de modo experimental y sin ninguna intención de hacerlo lucrativo por el momento. ¿Qué mejor manera de lograrlo que con una cámara y una bola de tennis?

**Lo que pretendemos implementar como solución es conocido en el medio de la informática como**Visual Tracking, que consiste en, básicamente, ubicar en un espacio y tiempo determinados el movimiento del objeto de estudio (en nuestro caso pelota de tennis) usando como medio de captura de datos una cámara conectada al computador, con la particularidad de que en este caso además del patrón de movimiento se sigue un patrón de color, en este caso el de la pelota de tennis.

## Aproximación al problema desde la Ingeniería de Sistemas y Computación ##
Sin duda los términos en los que hemos descrito el objetivo del documento dejan claro que hablamos de una solución a través de computadores. Necesitamos una cámara que obtiene imágenes de la realidad de las cuales obtenemos información a través de la modificación de dichas imágenes y de la aplicación de algoritmos para identificar en que parte de la imágen se encuentra la bola de tennis. Luego ese programa, el VTS, entrega una serie de coordenadas al juego, de manera que éste mueve la barra y verifica si hubo o no contacto con la bola, gestionando el movimiento de todas las partes del juego. Estaríamos hablando entonces, de un programa que sigue un algoritmo arrojando datos a manera de resultados, implementados en otro programa que usa esa información para que **[greenPong ](.md)** pueda ser jugado.

Entonces, se requiere una cámara, un VTS desarrollado en java, porque es el lenguaje al que más acceso tenemos todos en el momento, es de código abierto y multiplataformas, lo cual supone una futura gran acogida en cuanto a la aplicabilidad del proyecto. Para lograr esto usaremos el sistema operativo de libre distribución Linux Ubuntu,  pues permite manipular con mayor facilidad el acceso a los puertos, es decir la cámara, y existe mucha documentación en línea sobre el sistema operativo que puede resultar útil. Además, Ubuntu suele ser un sistema más eficiente y personalizable, ésto permite lograr un resultado final más fácil al haber menos restricciones. Por otro lado, Ubuntu permite la comunicación de procesos (hilos o threads) con facilidad y esto podría eventualmente ser necesario para la integración de la camara con el VTS y del VTS con Pong.

Adicional, Java resulta ser un lenguaje adecuado porque su documentación es amplia, es sencillo para la creación de interfaces gráficas y es un sistema ampliamente utilizado para el desarrollo de juegos. El hecho de que sea libre y de código abierto, resulta ser un aspecto fundamental para la filosofía de nuestro proyecto, pues **[greenPong ](.md)** será de igual manera una aplicación de código abierto y libre distribución, siempre que se reconozca la autoría y no se implemente con fines lucrativos.

Analizando entonces otras posibles soluciones cabe mencionar que los lenguajes que requieran compilarse para cada sistema operativo en particular no están entre los posibles lenguajes a usar pues romperáan con la posibilidad de que en el futuro esta forma experimental de interactividad pueda ser utilizada por otras personas. Además los lenguajes más conocidos de ese tipo resultan bastante más complejos que Java y no hacen parte de los cursos de programación que se toman en primer semestre. Otras soluciones como C# utilizando la plataforma mono (el equivalente a .NET en código abierto), tienen la dificultad de ser más nuevos, tener una sintaxis muy particular y requerir toda una plataforma para desarrollar en ellos y correr las aplicaciones resultantes, plataforma que no es tan utilizada como Java, además mono no llega todavia a la amplitud de .NET, y no está entre los planes ni la filosofía de éste proyecto utilizar un lenguaje propiedad de una compañia sin compromiso, con el desarrollo de software más accequible y con más enfoque social por el simple logro de la comunicación entre los hombres.

## Conocimientos necesarios de otras áreas del conocimiento ##
Para que este proyecto salga adelante se requiere pensar las formas en que matemáticamente se debe gestionar la información de los dos programas del proyecto para hacerlo de la mejor forma posible, logrando simplificar código y la utilización de recursos de la máquina en que se corran. Conceptos de geometría analítica pueden resultar útiles para gestionar la posición de los objetos del juego en el tablero y para sacar de dichas posiciones información, por ejemplo: Cuando un usuario pierde o cuando golpea la bola. Básicamente a través de la geometría y las matemáticas se pretende trazar cálculos de trayectoria, rebotes, dirección, y tratando de emplear modelos cercanos a la física (sin recrearlos totalmente), para las condiciones de rebote, movimiento y velocidad dentro del desarrollo de la mecánica del juego.

Los conocimientos de diseño sobre el manejo de la imagen, los efectos de aplicar un algoritmo sobre ella y la forma en que se puede llegar a cierto tipo de imagen a partir de otra serán necesarios para el VTS. Sin dichos conceptos, no se puede saber cómo aprovechar de la mejor forma utilidades como Java Advanced Imaging API o las librerias desarrolladas por terceros para manipulación de imágenes en aplicaciones Java.

## Actividades pensadas para desarrollar el proyecto ##

La actividad central sobre la cual gira el proyecto en esta primera fase es la investigación y documentación sobre los recursos de Java y el código necesario para desarrollar Pong inicialmente, y para entender la disposicion del sistema de VTS que se integraría con los otros elementos a futuro.

Las actividades posteriores son básicamente la creación del código del Pong y disposición de la cámara para unificarlos en el juego, además del diseño de algoritmos y cálculos que se emplearán para simular el movimiento de la pelota, el rebote, y demás elementos dinámicos necesarios.

Por lo tanto en la primera fase se profundizará e indagará acerca de la implementación de librerías para procesamiento y manipulación de imágenes en Java  (Advanced Imaging API), útil tanto como para la creación del Pong , como para el VTS que se desarrollará posteriormente. Se planea usar la librería _**swing**_ ofrecida por Java, pues es la más completa y útil para nuestro caso particular. La distribución de los elementos, a la hora de desarrollar el código de Pong, en el modelo del mundo como clases, paquetes, métodos y demás, estarán disponibles en el repositorio de subversiones, en la página web del proyecto.

## Avances del proyecto ##

Nuestro equipo de trabajo se ha comprometido profundamente con **[greenPong ](.md)**. Debido a nuestro afán por documentar el proyecto, hemos decidido crear desde el comienzo un wiki y un repositorio de subversiones para almacenar nuestro código fuente. Ambos pueden verse desde internet ingresando en http://code.google.com/p/greenpong. Como puede verse, la documentación y el código están hospedados en una herramienta web de google diseñada para facilitar el desarrollo de proyectos de código abierto.

Sobre como desarrollar el juego (que es la primer fase del proyecto y que está próxima a concluir según el calendario propuesto en la entrega preliminar) se han realizado ciertas decisiones sobre el modelo de clases en Java. Se han creado clases para las barras, la bola y un contenedor, una clase gestiona la entrada y la salida y se comunica con otra llamada Brain. Esa separación, que puede parecer exagerada, nos ha parecido adecuada pues está pensada para la futura integración con el VTS en la segunda fase de **[greenPong ](.md)**. Para el desarrollo del cerebro del programa también se han consultado otros clones de Pong disponibles gratuitamente en línea. Dichas fuentes están mencionadas en el listado de fuentes al final de este documento.

Como entorno de desarrollo se está utilizando eclipse 3.1 con un plugin para acceder a repositorio de subversiones permitiendo desarrollar a los integrantes del grupo por partes el código desde distintas ubicaciones geográficas. Eclipse corre sobre Ubuntu 6.06 Desktop version, por lo que se explicaba previamente respecto a Linux como sistema base del proyecto. Para compilar el código y empaquetarlo se usa Ant porque el modelo de proyecto usado por la universidad supuso muchos inconvenientes por no estar diseñado para cualquier plataforma, ant en cambio es gratuito y se puede correr en varios sitemas operativos.

Un libro de referencia a Java completo y reconocido ha sido enviado a todos los integrantes del proyecto para que puedan informarse sobre la creación de GUIs (interfaces gráficas de usuario) en este lenguaje de modo que puedan leer y justificar las líneas de código que se van agregando al respositorio de subversiones. Se trata del libro Thinking in Java de Bruce Eckel. También, dicho libro está referenciado al final del documento.

## Listado de fuentes ##
Dado que resulta útil saber de que se trata cada fuente y que todas son fuentes en línea se ha mencionado aquí URL, título y temática del documento. Esta lista está en constante actualización a través del wiki de **[greenPong ](.md)** y puede consultarse en línea en http://code.google.com/p/greenpong/wiki/FuentesDocumentacion.

  * http://www.mindviewinc.com/downloads/TIJ-3rd-edition4.0.zip Thinking in Java, ECKEL, Bruce. 3rd edition. Libro completo y extenso con código fuente y gratuito de referencia para el lenguaje java.

  * http://www.gamearchitect.net/Articles/GameObjects1.html Game Object Structure, Documento sobre como debe ser la estructura de clases, habla de juegos en C++, pero resulta util para decidir crear clases Box, Ball y Bar como entidades separada que se comunican y no heredan de un objeto general,ej gameObject.

  * http://www.jtech.ua.es/tutoriales/apuntes/sesion-ant-apuntes.htm Automatización con Ant, Explica la utilización de Ant para generar un archivo build.xml que compila el código java, lo empaqueta y lo ejecuta. build.xml ya ha sido creado y hace parte del código fuente.

  * http://www.onjava.com/pub/a/onjava/excerpt/anttdg2_ch11/index.html?page=1 Integrating Ant with Eclipse, Explica como hacer parte de un proyecto de eclipse un build.xml para Ant y explica como ejecutar desde este IDE la construcción de la aplicacion. build.xml ya ha sido creado en el proyecto y funciona.

  * http://www.javacooperation.gmxhome.de/TutorialStartEng.html The Java Game Development Tutorial.

  * http://www.xnet.se/javaTest/jPong/jPong.html Tablero pong, Este Pong se corre como un applet en el navegador y se usó para obtener las proporciones para los tamaños de los elementos de la interfaz gráfica que se mencionan en el documento del tema.

  * http://uguu.org/arc_pong.html Pong Source, Código de Pong que puede ser referencia de como implementar el juego.

  * http://patriot.net/~tvalesky/paddlegame.html Pong simplificado, juego de pong simplificado (1player) del que se puede estudiar la interfaz y la detección de colisiones y recepción de información del mouse.

  * http://java.sun.com/products/java-media/jai/forDevelopers/jaifaq.html Java Advanced Imaging API, Documentación del API de procesamiento avanzado de imagenes de Sun para Java (Inglés).

  * http://www.planetacodigo.com/planeta/date/2004/02/ Tratamiento de imagenes con Java, Artículo acerca del procesamiento imagenes en Java (Español).

  * http://www.javahispano.org/codelibsbin.type.action?type=libsbin Libreria de código, Código en java documentado en español que puede incorporarse en una aplicación. Útil para la documentación de los integrantes del proyecto en el lenguaje de programación.

  * http://www.cat.csiro.au/cmst/staff/jmr/thesis/ Tesis : " Attentive Visual Tracking and Trajectory Estimation for Dynamic Scene Segmentation".

  * http://www.javagaming.org/forums/ Foros de progrmaciòn en java con una comunidad muy amigable, para resolver cualquier duda.


## Metodología (para saber de qué se trata cada parte)(EXTRADOCUMENTO) ##

_"Los grupos deben realizar una investigación de la situación actual de la situación   seleccionada. Cuáles son sus componentes, cuáles sus relaciones._

_De otra parte, se debe reportar las propuestas de solución que existen, sus principales características._

_Toda la información recopilada debe ser correctamente citada y expuesta en el trabajo, sin ser transcrita, sino analizada para poder tomar alguna posición personal al respecto._

_Con qué disciplinas debe complementar la Ingeniería de Sistemas y Computación para generar una solución integral a la situación identificada._

_Con base a los requerimientos identificados, se debe formular un conjunto de actividades (y resultados de cada actividad) que, conjuntamente, contribuyan a la construcción de la solución "_


## Notas al equipo de trabajo (EXTRADOCUMENTO) ##
Borré la palabra versátil, no sé a que se refiere y Tiberio seguramente va a hacer una pregunta de diccionario y capciosa en torno al tema, por eso mejor ahorrarse términos que suenan muy 'ingeniéricos' pero que a algunos profesores no le agradan.