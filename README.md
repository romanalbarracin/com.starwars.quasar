# com.starwars.quasar

1. Supuestos
- Siempre se obtiene la informacion de los 3 satelites
- Los arreglos de mensajes siempre contienen el mismo tamaño 5
- La informacion de los satelites en el payload en el post del servicio topsecret siempre llega la información de los tres satelites

2. Crear Job en Jenkins con el nombre ***java_devops_mitocode** y de tipo **Multibranch Pipeline**

5. Conectar repositorio git del proyecto java de ejemplo con la ruta
```
git@github.com:romanalbarracin/com.mitocode.devops.git
```
6. Configurar las credenciales con el repositorio SSH con la llave privada proporcionada (archivo enviado por correo)

7. Programar ejecución del job de jenkins cada minuto

8. Para el deploy del war instalar el plugin **Deploy to container** y crear un job jenkins de tipo **Multibranch Pipeline** y alli crear la configuracion para desplegar en el servicio de tomcat:

	- Conectar al repositorio del proyecto
	- Configurar el **build** con maven y con Goals _clean install_
	- Configurar el **post-deploy** con _Deploy war/ear to a container_ con los valores
	  - WAR/EAR files: /var/jenkins_home/workspace/com.mitocode.devops/target/devops.war
	  - Context path: devops
	  - Containers/ Tomact URL: http://localhost:8888


Con estos pasos deberia funcionar correctamente.




