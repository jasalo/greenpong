#Describe como ingresar al codigo fuente desde eclipse utilizando SVN

# Instalacion de Subclipse #
**La instalacion del plugin para acceder via SVN al codigo fuente dese eclipse esta explicada para eclipse 3.1**

1. Click en Help -> Software updates -> Find and install.

2. Elegir search for new features to install.

3. Darle click al boton new remote site.

4. Poner como nombre "Subclipse para 3.1" y en url escribir la siguiente direccion sin espacios ni antes ni despues: http://subclipse.tigris.org/update_1.0.x

5. Activar el checkbutton de la parte inferior de la ventana donde dice "ignore features not appicable to this environment". Y ectivar tambien subclipse para 3.1 en el listado de fuentes de plugins.

6. Click en Finish.

7. En la ventana que se abrio desactivar el checkbutton "Show the latest version of a feature only"

8. Elegir para instalar subclipse 1.0.5

9. Cuando termine acepte la peticion que hace eclipse de reiniciar el IDE.

Terminado, tiene usted el plugin subclipse instalado en su IDE.

# Accediendo al código fuente #

Las siguientes son las instrucciones para poder obtener el codigo fuente desarrollado hasta el momento:

1.Elija New -> Other (ctrl + N).

2.Seleccione en la carpeta "SVN" "checkout projects from SVN".

3.**Si es la primera vez que accede al codigo fuente** elija create a new repository location, en caso contrario elija "Use a existing repository location".

4.Use como url esta: **_https://greenpong.googlecode.com/svn/trunk/greenpong/_ Verifique  no haber dejado espacios antes ni despues**. Seleccione la carpeta greenpong que contiene todo el codigo fuente, los archivos README y los archivos de referencia.

5.Use como usuario su user de gmail y como clave la que sale al ingresar a la pagina http://code.google.com/hosting/settings **ESTA CLAVE ES SOLO PARA ADMINISTRADORES NO LA DE A CONOCER**

6.Seleccione checkout as a project new project in the workspace y llameno greenpong (Ojo con las mayusculas). Siga el asistente y le quedara el proyecto en local.

7.Para subir el codigo que acaba de editar o para obtener cualquier informacion remota click derecho sobre el proyecto y use las opciones del menu edit ojo con los cuidados realizados porq se realizan en el hosting de google.