# Backend_Imagineria_Web

## Descripcion

Gestión sobre una aplicación de imagineria, que para que no sepan de que se trata, es el arte de la escultura religiosa en la que se extiende en varias categorias. Por nosotros en nuestro entorno son muy populares las imágenes procesionales en la que salen en Semana Santa

## Tecnología y lenguaje utilizado:
Para el desarrollo de la aplicación, se han utilizado los siguientes elementos:
- **Spring Boot** como framework.
- **Java** para el desarrollo del código que atiende las peticiones a la API.
- **PostgreSQL** para la gestión de la base de datos.
- **DockerCompose** : para levantarlo se necesita tener docker encendido  poner en la consola docker compose up . 

## Entorno de desarrollo y ejecución:
Para el desarrollo del proyecto, se ha utilizado el entorno de desarrollo **IntelliJ IDEA**. Para su ejecución en dicho entorno, abrimos el proyecto y,
en la barra superior, en la parte derecha, en los primeros iconos, seleccionamos sobre **Curent File**, y luego **Edit Configuration**. Al aparecer la nueva
ventana, pulsamos, en el menú superior, en el icono **+**, en el desplegable, seleccionamos **Maven**, y en la opción **Run**, en **Command line**, escribimos
**spring-boot:run** y lo seleccionamos en el menú. Pulsamos en **Aplicar** y **Aceptar**.
Ahora solo tendremos que pulsar el icono de **Play** junto a **imagineria_web spring-boot:run**, y, una vez finalice la ejecución en consola, tendremos el proyecto ejecutado
y accesible desde la dirección **http://localhost:8080/** como ruta raíz.

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

## Dificultades o Errores duante el proceso
- Se ha queriodo implementar la gestión de favoritos entre la relación de Obras y usuarios, el cual tenemos los siguientes endpoint: Un usuario logeado marca como favorito a una obra existente, la obra marcada se añade a la lista de favoritos que tiene un usuario, un usuario puede ver todas sus obras  favoritas, el usuario elimina un favorito por lo tanto la lista se actualiza. [**EN PROCESO**]
- El endpoint de la subida de fichero en el endpoint de creación de una obra, porta algunos errores ya que ni se me sube la imagen y no encuentra el controllador de FileController. [**EN PROCESO**]

## Dificultades con el Fronted
- Las relaciones entre imaginero , categorias con obras se ha tenido que comentar por problemas de la creacion de una obra en el fronte, pero en la gestión del backend va todo perfecto, sin errores ni dificultades existente. [**EN PROCESO**]

## Nuevas Tecnologias
- **Seguridad**: Se ha implementado seguridad en la que se porta de dos roles, "ADMIN" y "ROLE", en las que cada una cuentan con endpoints propios y que solo se pueden gestionar si el usuario logeado tiene su respectivo rol
- **Validacion**: Se ha implementado la validacion de errores con anotaciones propias como que no se puede añadir una obra si tiene un precio negativo
- **Excepcion**: Se ha implemntado la excepcion de errores si no se consigue realiza los endpoints
- **Paginación**: Se ha implentado la paginación en Obras

## Rutas disponible de ADMIN:
### Obras:
- **GET: http://localhost:8080/obras/**: Obtiene el listado completo de tipos de obras.
- **POST: http://localhost:8080/obras/**: Crea una nueba obra.
- **PUT: http://localhost:8080/obras/{id}**: Modifica una obra.
- **DELETE: http://localhost:8080/obras/{id}**: Borra una obra.
- **GET: http://localhost:8080/obras/{id}**: Obtiene la información de una obra, buscada por su ID.

### Imaginero:
- **GET: http://localhost:8080/imaginero/**: Obtiene el listado completo de imagineros.
- **POST: http://localhost:8080/imaginero/**: Crea un nuevo imaginero.
- **PUT: http://localhost:8080/imaginero/{id}**: Modifica un imaginero, buscado por su ID.
- **DELETE: http://localhost:8080/imaginero/{id}**: Borra un imaginero, buscado por su ID.
- **GET: http://localhost:8080/imaginero/{id}**: Obtiene la información de un imaginero, buscada por su ID.

### Categoria:
- **GET: http://localhost:8080/categoria/**: Obtiene el listado completo de categorias.
- **POST: http://localhost:8080/categoria/**: Crea una nueva categoria.
- **PUT: http://localhost:8080/categoria/{id}**: Modifica una categoria, buscada por su ID.
- **DELETE: http://localhost:8080/categoria/{id}**: Borra una categoria, buscada por su ID.
- **GET: http://localhost:8080/categoria/{id}**: Obtiene la información de una categoria, buscada por su ID.

### User:
- **POST: http://localhost:8080/auth/register/admin**: Obtiene el listado completo de categorias.
- **PUT: http://localhost:8080/user/changedPassword{id}**: Modifica una categoria, buscada por su ID.
- **DELETE: http://localhost:8080/categoria/{id}**: Borra una categoria, buscada por su ID.
- **GET: http://localhost:8080/me/{id}**: Obtiene la información del usuario logeado.
