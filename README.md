# Imagineria_Web

## Descripcion

Proyecto Integrado fin de curso de 2ºDAM en el cual se ha realizado un desarrollo extenso de la API, para también desarrollar la app móvil en Android Studio y la web con Angular.
El proyecto trata sobre un catálogo cofrade en el cual los usuarios participan abiertamente en la edición y creación de obras nuevas para compartir.

Al iniciar la aplicación tanto en web como en mólvil, el username es: **apbarba y el password **soso

## Tecnología y lenguaje utilizado:
Para el desarrollo de la aplicación, se han utilizado los siguientes elementos:
- **Spring Boot** como framework.
- **Java** para el desarrollo del código que atiende las peticiones a la API y de la aplicación movil.
- **Angular** para el desarrollo de la aplicación web
- **PostgreSQL** para la gestión de la base de datos.
- **DockerCompose** : para levantarlo se necesita tener docker encendido  poner en la consola docker compose up . 

## Entorno de desarrollo y ejecución:
Para el desarrollo del proyecto, se ha utilizado el entorno de desarrollo **IntelliJ IDEA**. Para su ejecución en dicho entorno, abrimos el proyecto y,
en la barra superior, en la parte derecha, en los primeros iconos, seleccionamos sobre **Curent File**, y luego **Edit Configuration**. Al aparecer la nueva
ventana, pulsamos, en el menú superior, en el icono **+**, en el desplegable, seleccionamos **Maven**, y en la opción **Run**, en **Command line**, escribimos
**spring-boot:run** y lo seleccionamos en el menú. Pulsamos en **Aplicar** y **Aceptar**.
Ahora solo tendremos que pulsar el icono de **Play** junto a **imagineria_web spring-boot:run**, y, una vez finalice la ejecución en consola, tendremos el proyecto ejecutado
y accesible desde la dirección **http://localhost:8080/** como ruta raíz.

De igual manera se ha desarrollado la app movil en el entorno de desarrollo **Android Studio**, que tiene las mismas funciones y pasos para la ejecución que en la API.

Para la app web, se requiere estar dentro de la carpeta de angular para poder realizar su ejecución, ahí adentro iniciamos el comando de ng serve, y acontinuación nos dirigimos al buscador y ponermoa la ruta descrita anteriormente. A partir de ahí, podemos interacturar con la aplicación.

## Pruebas:
Para poder probar la API, tendremos dos vías principales:
- **Documentación del proyecto en Swagger**: Accederemos a través de la ruta **http://localhost:8080/swagger-ui.html**. Aquí, podremos probar todos los endpoints
  disponibles en la API, y ver ejemplos de retorno.
- **Aplicación de Postman**: Dentro del proyecto, se encuentra el archivo **imagineria_web.postman_collection.json**. Este archivo, podremos importarlo en las colecciones de Postman,
  en el que hay preparadas una serie de peticiones a todos los posibles métodos de la API. En las peticiones POST y PUT, cuando la seleccionemos, al pulsar en **Body**, podremos
  indicar el cuerpo que se envía en la petición para crear o modificar algún recurso, en formato JSON.
- **Import.sql**: Dentro del resource hemos creado un archivo import el cual contiene datos ya importados de categoria, obras e imagineros

## Organización del proyecto:
En la carpeta principal nos encontramos diferentes elementos a tener en cuenta:
- **src**: Es la carpeta donde se aloja todo el código fuente utilizado en el desarrollo de la aplicación.
- **imagineria_web.postman_collection.json**: Es una colección de Postman, que podremos importar en dicho programa, y que nos permitirá acceder a los distintos endpoints de la API para
  probar todas sus funcionalidades.
- En nuestra colección de Postman ya está preterminado nuestro usuario Admin, aunque se puede crear también, también hay que describir que hay que utilizar el token del usuario que se ha loggeado para que se puedan utilizar las peticiones.
- **Documentación**: En la que se hace entrega de la documentación requerida y con todo nuestro seguimiento e idea del proyecto más detallada









