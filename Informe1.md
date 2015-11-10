Este documento contiene la información que debe entregarse como primer informe.
Fecha de entrega: Feb 16.
# Recursos que se van a utilizar
# Fases del proyecto
# Integrantes del proyecto
# Objetivos y contexto
# Estructura o Métodos de desarollo
# DOFA (Debilidades, fortalezas, amenazas, oportunidades)
# Resultados Esperados


Tabla de contenidos

  * 1 Recursos que se van a Utilizar
  * 2 Fases del proyecto
  * 3 Integrantes del proyecto
  * 4 Objetivos Y Contexto
  * 5 Estructura o Métodos de desarrollo
  * 6 DOFA
  * 7 Resultados Esperados


# Recursos que se van a Utilizar #

> Un wiki usando Mediawiki (OpenSource) con acceso a todos los integrantes del equipo para el desarrollo conjunto de documentación y la comunicación entre integrantes. Se puede otorgar acceso al profesor para que monitoree y observe el desarrollo del trabajo.

> Una camara, comenzaremos usando una de resolucion media para ir bajando hasta (si es posible) usar un web cam.

> De uno a tres PCs

> ¿Por qué uno? , Lo ideal sería usar uno solo para todo llámese capturar la informacón de la cam, aplicarle los algoritmos de "filtrado de información", pasarlos al motro del juego, correr el juego y mostrar el juego en tiempo real.

> ¿Por qué dos? uno para capturar la información y filtrarla y otro para correr el juego y "mostrarlo en pantalla"

> ¿Por qué tres?, Suponiendo que hagamos un multiplayer serian necesarios dos para capturar información de sus respectivas camaras y filtrarla, y otro que funcione como central donde simplemente se recibe la información filtrada y se dedica a correr el juego y mostrarlo.


# Fases del proyecto #

**Fase 1: Desarrollo de los elementos basicos**

Duración: Hasta el lunes 12 de Marzo.

En esta fase procederemos a desarrollar el Pong(1) y el sistema de captura de la camara en paralelo, eso incluye la modificacion del driver si lo desarrollamos en linux (en pro de la facilidad del proyecto, y en coontra de la posible comercialización del mismo) o la adaptación de un programa de captura a nuestras necesidades en Win XP (situación contraria que en linux).

Al final de la fase 1, el pong deberá ser jugable desde mouse(2), el sistema de tracking debe ser capaz de reconocer en tiempo real la ubicación exacta de la pelota, y mostrar una serie de coordenadas (al menos 12 coordenadas por segundo cp).

En caso de terminar primero una cosa que la otra, los integrantes de el equipo que ha finalizado se uniran a los otros para ganar tiempo.

Documento sobre el desarrollo del pong detallado (Fase 1.1)


**Fase 2: Integración de ambos elementos**

Duración: Del lunes 12 de Marzo al domingo 25.


Esta es la fase fundamental del proyecto greenPong, ya que el punto de innovación radica justamente en este instante del desarrollo, Lograr que las dos tecnologías trabajadas por el grupo puedan funcionar como una sola es el gran reto para presentar un elemento novedoso dentro del público.

En resumen, lo que se hará en este momento, es hacer que la tecnología del sistema de tracking puede pasar unas coordenadas en x para el pong, y lograr mover la barra del jugador en respuesta a los movimientos de la pelota, esto es, darle al jugador la posibilidad de operar el Pong desde el sistema de contrastes desarrollado en el momento anterior (ver Fase I).

Al finalizar esta etapa del proceso de desarrollo de greenPong, cuyo tiempo de desarrollo podría extenderse un poco mas de lo planificado, tendríamos prácticamente listo el sistema jugador-interfaz para ser mostrado y empleado sin ninguna dificultad, posteriormente, lo que logremos mejorar y agregar al sistema es ganancia (ver Fase 3)

<carreta included xD>



**Fase 3: Beta testing, optimización y mejora estetica**
Duración: Del lunes 26 de Marzo al 2 de Mayo.

Mejora de algoritmos: entre menos factores podamos eliminar para el reconocimiento de la bola mucho mejor, asi podremos jugar en mas ambientes y con mas objetos.

Inclusión de graficas, mejoras en la jugabilidad, música, sonidos, etc...

Hay que tener en cuienta que esto es una proyeccion a futuri, ams que una fase del proyecto, es el trabajo final, refinamiento, orden, diseño atractivo, logistica, organizacion, aumento del campo de acción, interactividad, musica, sonido, conectividad, etc, etc


# Integrantes del proyecto #
# Ivan Duarte (Hydro)
# Javier Lozano (Jasalo)
# David Pelaez (Dpt)
# Francisco Pinzon (Pacho)
# Andres Felipe Polania (pipe)
# Juan Pablo Reyes (Daraexus)


# Objetivos #
# Lograr la integración total y funcional de la pelota de tennis con el pc.
# Demostrar a la comunidad andina que no todos los proyectos de Ing. de Sistemas tienen que ser "Gestión de bases de datos"


# Innovar trabajando a partir de tecnologias existentes, integrándolas en un sistema eficiente, inteligente y novedoso, atractivo para el usuario y bastante práctico y ergonómico.
# Enteder y profundizar en las áreas de computacion gráfica y computación visual, de las cuales pueden surgir bastantes aplicaciones prácticas.
# Realizar un proyecto que puede resultar atractivo a la hora de exponerlo por ser lúdico

anexo
# Partir de aplicaciones cotidianas y novedosas que pueden extenderse a diferentes campos de acción, en pro de mejorar las herramientas y sistemas con que se cuenta día a día.
# Diseñar planes de desarrollo eficientes que den como resultado buenos productos.
# Explorar las distintas herramientas de la tecnología que pueden ser integradas entre si para obtener grandes resultados.


# Contexto #

Debemos pensar en un mundo en el cual la tecnologia cada vez ocupa un lugar de mayor importancia, dia a dia el mundo depende mas de los desarrollos e innovaciones, y cada vez mas, los grandes desarrollos de la ciencia estan al alcance de la mano. A eso apunta el proyecto [greePong](greePong.md)de nuestro grupo, demostrar que integrar tecnologias novedosas para lograr usos prácticos dentro de la cotidianidad.

Claro está, [greenPong](greenPong.md) está enfocado en los videojugadores, pero esto no significa necesariamente que tenga una restricción de edades más que la ligadura a una coordinacioón motora suficiente, muchas persoans aun recuerdan el viejo Pong en el tiempo de las maquinas de Arcadia, (aunque es realmente triste que pocos conozcan su historia, como dato curioso, nació de una máquina de oscilaciones mientras dos trabajadores jugaban con esta) y como de chicos, ese y los marcianitos) exigian todo un esfuerzo motriz para mover la palanca y vencer a la máquina.
pong.gif

aqui una imagen del viejo pong basico, para quienes aun lo recuerdan, pixelado y de una dificultad apreciable, sacado de "http://www.gifmania.com"

Ahora, mirando un poco las tecnologias de computacion visual (visual tracking) podemos observar que son innovaciones no tan lejanas o descabelladas que estan al alcance de la mano, y con trabajo y práctica, puede incorporarse junto con otras aplicaciones, en nuestro proyecto, el juego de Pong, y otros juegos por el estilo.

El punto central, partimos de dos cosas preexistentes en el mundo de la tecnologia, y nos planteamos el reto de unificarlos en un solo. Porque el campo de los videojuegos? porque la aplicacion mas directa y mas atractiva visualmente para una generacion de jóvenes lideres industriales que tuvieron contacto directo con los videojuegos, consiste precisamente en eso, un videojuego, y la opcion de la interactividad entre movimiento fisico y usuario para lograr algun efecto percibible dentro de la aplicacion no es algo tampoco que no se haya explorado, precisamente NINTENDO, con su WII introdujo un control inalámbrico que lee ciertas carácteristicas del movimiento del usuario para trasnformarlo en acciones y señales dentro de los distintos videojuegos, nuestro enfoque es el mismo, pero menos complejo.

El hecho de que hayamos escogido el videojuego Pong como punto de partida no implica necesariamente limitarnos a aplicaciones didácticas, es un campo de acción gráfico que puede incluso desembocar en diversos usos en el futuro de la computación visual para el hogar,podemos sonreir ante la sugerencia de mover el mouse del computador a través del escritorio solo usando el dedo, y muchas otras variantes que no suenan tan descabelladas viendo a dos niños enfrentadose con una pelota de tenis en un Pong
[editar](editar.md)

# Estructura o Métodos de desarrollo #

La estructura del proyecto ha sido categorizada en 3 partes:
# Parte 1: Estudio, diseño y aplicación del ambiente de juego.
# Parte 2: Diseño, creación y programación del juego.
# Parte 3: Estudio del sistema de integración Juego - Ambiente de Juego


Las 3 partes del proyecto se desarrollarán en simultánea y con la participación de todos los miembros del grupo en ellas, enfocándose algunos en una parte más que en las otras 2, pero de igual manera contribuyendo al completo funcionamiento del proyecto.


**Parte 1: Estudio, diseño y aplicación del ambiente de juego**

- El estudio del ambiente de juego consistirá en indagar acerca de las tendencias de juego actuales, para ser adaptadas a un nuevo estilo de juego que llame la atención por su uso, versatilidad, facilidad de manejo, creatividad, funcionalidad y apariencia.
- El diseño del ambiente de juego dependerá de los resultados obtenidos en la etapa previa de estudio del mismo, donde se traerán a colación las características que resalten por ser más apropiadas y preferidas en el grupo de gente escogida para ser encuestada, a la hora de cuestionarles qué preferirían ellos para utilizar un nuevo estilo de juego como el que propone [greenPong](greenPong.md).
- La aplicación del ambiente de juego se hará en el período de pruebas de [greenPong](greenPong.md), donde se probará la confiabilidad, facilidad de uso y demás características pertinentes de esta parte del proyecto.

Aunque se planea realizar el estudio antes mencionado, la idea inicial propuesta para el proyecto es:
1. Un jugador usando una pelota de tenis a manera de "control", la cual hará posible el movimiento de la barra de Pong en el juego, para evitar dejar pasar la pelota y seguir cumpliendo el objetivo de juego, permitiéndola rebotar.
2. Delante del jugador un monitor donde se muestre el tablero de juego, una cámara encima que capturará su movimientos, y quizás un monitor adicional que muestre el sistema de captura.
3. Detrás del jugador habrá una tela negra que servirá de fondo contrastante con el verde de la pelota, para hacer aún más preciso el sistema de detección, que hasta ahora se planea que funcione mediante diferenciación de contrastes de colores.


**Parte 2: Diseño y programación del juego**

- El diseño del juego no representará mayor dificultad, ya que se iniciará con la estructura más básica, que propone el Pong original, y se modificará para adptarla quizás a un entorno de juego visiblemente más agradable.
- La programación del juego consistirá de crear un nuevo Pong en un lenguaje de programación multiplataforma, preferiblemente Java™, para ser usado en un entorno Linux, que facilite la futura integración con el sistema de detección, ya que Linux permite una libre modificación de los drivers y demás componentes del sistema, a diferencia de sistemas operativos más comunmente usados para otras tareas como Windows y Mac OS.


**Parte 3: Estudio y aplicación del sistema de integración Juego - Ambiente de Juego**

- El estudio del sistema de integración se hará a partir de información tomada de libros, investigaciones, internet y demás fuentes útiles y confiables, que hablen del tema que hasta ahora se convierte en columna vertebral de nuestro proceso de integración: visual tracking. Luego de reunir los datos suficientes, esperamos modificar componentes del sistema en Linux, para lograr obtener una interacción exitosa entre la plataforma de juego pensada y el juego antes desarrollado; eso implicaría modificación de drivers open source, que posteriormente liberaríamos bajo licencia GPL.
- La aplicación inicial involucraría el objeto de estudio hasta ahora pensado para el proyecto, es decir, lograr jugra [greenPong](greenPong.md) empleando los medios desarrollados previamente. Una futura aplicabilidad es buscar abrirse a implementar esta estructura de juego en otros videojuegos que respresenten mayor complejidad de uso y desarrollo, o quizás no sólo pueda ser aplicada esta tecnología al campo de los videojuegos, sino tal vez a otros programas para el computador o la vida diaria, desde tareas comunes hasta procesos más avanzados.


# DOFA #

Debilidades:

La poca experiencia que tenemos como ingenieros de sistemas puede ser un obstáculo en el momento de materializar las ideas, es fundamental contar con recursos como: libros, textos, entre otros para que de esa forma poder adquirir el conocimiento.


Oportunidades:

Expo-andes esa la excusa para que el trabajo en equipo pueda demostrar el gran poder que tiene, se permitirá el desarrollo de aptitudes como el liderazgo, responsabilidad, trabajo en equipo, sinergia, entre otros

El conocimiento de nuevas tecnologias y su implementación, para poder estar el tanto con el desarrollo y los avances.


Fortalezas:

En la actualidad la tecnología que vamos a implementar en el proyecto, lleva muchos años en el mercado, la información que existe acerca de TRACKING VISUAL es muy abundante, la (no tengo la presentacion de flash)!!!

El trabajo en equipo es una gran ventaja, ya que surgen numerosas ideas que puede mejorar el proyecto en curso.


Amenazas:

El tiempo es un factor muy importante en este proyecto las fechas ya están definidas, es nuestro trabajo cumplirlas y de esto dependerá el éxito del proyecto. La generación de problemas por falta de experiencia en el tema puede afectar en resultado de este proyecto.Es indispensable la organización del grupo de trabajo, cada integrante tiene que cumplir con las fechas y con las tareas asignadas.

La competencia no es debil, ademas que no hay que olvidarse que cada día surgen nuevas tecnologias y métodos diferentes de desarrollo.

# Resultados Esperados #

Greenpong espera terminar como un proyecto lúdico desarrollado por los integrantes del proyecto o utilizando porciones de código ya desarollado y disponible según sus licencias al público (GPL preferiblemente) sin fines comerciales (En un principio) que permita jugar pong con una pelota de tennis.

Se espera tener pong desarrollado desde cero por lo integrantes y manejable desde el mouse. Además es fundamental que se halla como mínimo logrado avanzar en el desarrollo del sistema de tracking de la bola de tennis y que den los mínimos requerimientos técnicos necesarios para que pueda hacerse realidad el sistema de tracking. Sin embargo, dados nuestros conocimientos no esperamos tener un sistema de tracking eficiente, terminado e integrado al juego obligatoriamente sino como mínimo avanzar en su desarrollo. A pesar de ésto puede eventualmente llegarse a tener el sistema de tracking al final de semestre, esa es una espectativa del grupo sin embargo no es un resultado esperado oficial. Espero opinión sobre este punto en particular