# PARCIAL-AREP2
Diseñé, construya y despliegue un aplicación web para investigar la conjetura de Collatz. 
El programa debe estar desplegado en un microcontenedor Docker corriendo en AWS. 
LAs tecnologías usadas en la solución deben ser maven, git, github, maven, sparkjava, html5, y js. No use liberías adicionales.

## :newspaper: DISEÑO 

Aplicación web para investigar la conjetura de Collatz

## :mag_right: ARQUITECTURA

1. Construya una aplicación web para investigar este problema. La aplicación debe tener esta arquitectura:
2. Cliente asíncrono que corra en el Browser escrito en en HTML5 y JS (No use librerías, solo html JS básico). El cliente recibe un número y muestra la secuencia de Collatz generada a partir de ese número.
3. El cliente NO COMPUTA LA SECUENCIA DIRECTAMENTE, sino que la delega a un serviucio REST corrieno en AWS.
4. Servicio REST para construcción de la secuencia de Collatz, el servicio puede ser GET o POST. El servicio recibe el número inicial en la variable del query con nombre "value".

****
## Empezando

### 🛠️ Abre y ejecuta el proyecto

**1. Para empezar se clona el repositorio colocando el siguiente comando**

```
git clone https://github.com/carol695/PARCIAL-AREP2.git
```

**2. Ya clonado el repositorio abrimos el laboratorio utilizando cualquier de los siguientes IDE.**

* Intellij.
* eclipse.
* visual Studio code. 

**3. Luego debe redirigirse por medio de la terminal al directorio en donde se clonó el proyecto la cual contendrá el archivo pom.xml. Una vez ubicado en este directorio se debe compilar el programa, para esto, utilice el siguiente comando:**

```
mvn package
```
### :bulb: Construcción 

1. Se crea un proyecto con las siguientes clases: 

![image](https://user-images.githubusercontent.com/63822072/229214327-323aae2b-67af-4969-aa9b-84053f9b7b79.png)

2. Se configura el pom y se genera las diferentes dependencias en .target

![image](https://user-images.githubusercontent.com/63822072/229214399-321634c9-e091-4ab8-9bec-2346d73e649f.png)

4. Al correr se verá así: 

![image](https://user-images.githubusercontent.com/63822072/229214512-ca0f5b9c-0358-48e7-bf36-828328f4bd53.png)

![image](https://user-images.githubusercontent.com/63822072/229214582-3a3818a5-f08d-4350-ad15-32045cd54549.png)

### Creación de contenedores

5. Se genera el siguiente archivo **Dockerfile**

![image](https://user-images.githubusercontent.com/63822072/229214682-924feba4-b9e9-4117-a80f-d3bb650d3a18.png)

6. Creación de la imagen de Docker

![image](https://user-images.githubusercontent.com/63822072/229214919-e09ba594-e2f1-4799-b86b-1f3ce10312eb.png)

![image](https://user-images.githubusercontent.com/63822072/229215088-a9e1ccc7-7dc4-4487-9e6f-01f886919a47.png)

![image](https://user-images.githubusercontent.com/63822072/229215623-e0e06b47-6284-408d-b548-e977a255c469.png)


7. Luego de crear 1 instancia de un contenedor docker, nos aseguramos que esta corriendo 

![image](https://user-images.githubusercontent.com/63822072/229215166-926db12f-93bf-424f-90d0-70d242b21db0.png)

8. Creamos un archivo llamado docker-compose.yml con un servicio

![image](https://user-images.githubusercontent.com/63822072/229215231-7c9c13ec-4a0b-468b-a938-d9e8a548c614.png)

9. Al ejecutarse docker-compose up -d se mira así:

![image](https://user-images.githubusercontent.com/63822072/229215312-5e40805f-2cc2-4fd1-9e29-e13e7a0c3bed.png)

![image](https://user-images.githubusercontent.com/63822072/229215563-3acd6585-7c32-4aff-980f-18a881f34656.png)


### Creación de servicios 

Al ejecutar docker ps, se verá así: 

![image](https://user-images.githubusercontent.com/63822072/229215729-a0c43b43-2d7c-4771-9d49-711e8212a4d3.png)

### Creación de repositorio y cargar imagenes a DockerHub

Para subir una imagen a DockerHub se debera tener una cuenta creada en el mismo, y crear 1 repositorio. 

![image](https://user-images.githubusercontent.com/63822072/229215920-42f76a95-74e4-4bec-aa4a-6c54e897bbfe.png)

Primero deberemos iniciar sesión en docker mediante el comando

```
docker login
```

Pondremos nuestras credenciales, y ahora asociaremos las imagenes al repositorio creado mediante el comando:

```
docker tag dockersparkprimer carolcely14/parcialarep-2
```

Y ahora subiremos las imagenes mediante el comando

```
docker push carolcely14/parcialarep-2:latest

![image](https://user-images.githubusercontent.com/63822072/229216107-d5c46abf-1e2b-414b-8205-5fd10c88b9a1.png)

### Descargar contenedores en EC2

Para descargar los contenedores y instalarlos en una maquina EC2, primero deberemos crear una instancia EC2, 
crear un certificado y iniciar sesion mediante el protocolo ssh en una terminal, si su sistema operativo es Windows se recomienda 
el uso de una terminal Linux descargada, para este caso se utilizara el mismo GitBash

![image](https://user-images.githubusercontent.com/63822072/229216255-282f25b1-f9ca-4ed8-8709-c1b9d5a90f25.png)

Creación de un par de claves y grupo de seguridad 

![image](https://user-images.githubusercontent.com/63822072/229216411-6d539193-132d-449b-a3c0-fb1967433f52.png)

Mediante la consola virtual de AWS, ingresamo a ella e instalamos Docker 

![image](https://user-images.githubusercontent.com/63822072/229216651-dc01739b-5e30-497c-ac7d-23560aa1eb4c.png)

Procederemos a actualizar el sistema mediante el comando

```
sudo yum update
```
Y a descargar docker en nuestra instancia de EC2 mediante el comando

```
sudo yum install docker
```

Le daremos permisos de ejecución en el sistema mediante el comando:

```
sudo service start docker
```

y cambiar los permisos de usuario del usuario user-ec2 con el siguiente comando:

```
sudo usermod -a -G docker ec2-user
```
Nos desconectaremos de la instancia de EC2 con exit para que los cambios surtan efecto, y nos volveremos a conectar para descargar las imágenes de DockerHub y ejecutarlas mediante el siguiente comando:

docker run -d -p 42000:6000 --name firstdockerimageaws carolcely14/parcialarep-2

y mediante la direccion podremos acceder al servicio desplegado mediante la pagina web

http://ec2-18-234-163-173.compute-1.amazonaws.com:42000

![image](https://user-images.githubusercontent.com/63822072/229216872-17d86fc7-741c-4245-9696-8167fcecf8ea.png)

Finalmente hacemos cualquier consulta: 

![image](https://user-images.githubusercontent.com/63822072/229217013-d692a05b-499b-4e95-8764-928781109a24.png)

![image](https://user-images.githubusercontent.com/63822072/229217054-e89c5d4d-913f-4bcc-b839-ffdb9f011efe.png)

****
### :chart_with_downwards_trend: Prerrequisitos

-   [Git](https://git-scm.com/downloads) - Sistema de control de versiones
-   [Maven](https://maven.apache.org/download.cgi) - Gestor de dependencias
-   [Java 8](https://www.java.com/download/ie_manual.jsp) - Entorno de desarrollo
-   [Intellij Idea](https://www.jetbrains.com/es-es/idea/download/) (Opcional)

****

### :bulb: Construido con

* [Maven](https://maven.apache.org/) - Dependency Management
* [AWS](https://aws.amazon.com/) - Instancia EC2
* [DOCKER](https://www.docker.com/)

## :mag_right: Versionamiento

Para definir el versionamiento se pudo obserar los tags del repositorio, y el versionaiento es 1.0 

## :woman: Actores

* **Carol Tatiana Cely Rodriguez** 







