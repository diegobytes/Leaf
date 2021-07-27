[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Java](https://github.com/aleen42/badges/raw/master/src/java.svg)](https://www.java.com)
# Leaf

Leaf es un proyecto desarrollado con el objetivo de cubrir necesidades asociadas a la carrera 
Ingeniería Agroambiental, facilitando el almacenamiento y organización de datos relacionados a 
salidas de campo, observaciones ambientales y actividades similares.
Entre los usos que fueron pensados al crear la aplicación se encuentran:
+ Poder registrar usuarios en la aplicación y que estos puedan volver a ingresar usando el 
usuario que registraron.
+ Que los usuarios registrados tengan diferentes niveles de acceso según el rol que tienen.
+ Modificar a los usuarios, administrar a los usuarios que usan la aplicación, brindar más 
funcionalidades cambiándoles el rol.
+ Diseñar formularios específicos acordes a las actividades de campo que van a realizar.
+ Diseñar casillas en base a los parámetros que quieren registrar.
+ Modificar y eliminar los formularios y las casillas creadas.
+ Asignarle a los formularios las casillas que diseñaron previamente.
+ Cargar los formularios cuando realicen las actividades, pudiendo llenar las casillas con los 
datos que obtuvieron mediante las observaciones en las actividades.
+ Visualizar los datos en las funcionalidades de la aplicación

# Diagrama entidad-relación
<br></br>

<img src="https://github.com/diegobytes/Leaf/blob/main/img/mer.jpg" alt="mer" style="float: left; margin-right: 10px;"/>
<br></br>


# Tecnologías utilizadas
<img src="https://github.com/diegobytes/Leaf/blob/main/img/java.jpg" alt="java" style="float: left; margin-right: 10px;" width="100"/>

La aplicación fue desarrollada en Java, utilizando Java Development Kit 15.
<br></br>

<img src="https://github.com/diegobytes/Leaf/blob/main/img/hibernate.jpg" alt="hibernate" style="float: left; margin-right: 10px;" width="100"/>

Para la persistencia de datos en la base de datos, se utilizó la librería Hibernate en su versión 5.3.17
<br></br>

<img src="https://github.com/diegobytes/Leaf/blob/main/img/wildfly.jpg" alt="wildfly" style="float: left; margin-right: 10px;" width="150"/>

Como servidor para la aplicación se utilizó WildFly en su versión 20. Para crear el Datasource (conexión del servidor con la base de 
datos) se utilizó el driver OJDBC en su versión 8.
<br></br>

<img src="https://github.com/diegobytes/Leaf/blob/main/img/sqldeveloper.jpg" alt="sqldeveloper" style="float: left; margin-right: 10px;" width="100"/>

La base de datos utilizada es Oracle Database 18c Express.
Para acceder a la base de datos, visualizar la estructura de las tablas, hacer consultas y verificar
que los datos se almacenan correctamente se utilizó la herramienta SQL Developer en su versión 19.4.
<br></br>

# Ejemplos

Luego de registrar un usuario, podemos crear formularios en base a la actividad que se vaya a realizar

![formulario](https://github.com/diegobytes/Leaf/blob/main/img/formulario.gif)
<br></br>
Cuando tengamos actividades cargadas en los formularios, podemos filtrarlas dinámicamente con todos los filtros disponibles

![actividades](https://github.com/diegobytes/Leaf/blob/main/img/actividades.gif)
