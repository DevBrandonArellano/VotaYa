# VotaYa - Plataforma de Votación Digital

Bienvenido a VotaYa, una plataforma de votación digital segura y confiable. Este proyecto implementa un sistema de votación electrónica basado en una arquitectura de microservicios, diseñado para garantizar la integridad, seguridad y auditabilidad de cada voto.

## Características Principales

- **Autenticación Segura**: Simula un mecanismo de autenticación basado en certificados digitales para garantizar que solo los votantes autorizados puedan participar.
- **Emisión de Voto Asíncrona**: Procesa los votos de manera asíncrona para asegurar una alta disponibilidad y una experiencia de usuario fluida.
- **Registro Inmutable**: Cada voto es registrado en un libro contable (ledger) para futuras auditorías.
- **Resultados en Tiempo Real**: Provee resultados agregados que se pueden consultar en cualquier momento.
- **Certificado de Votación**: Permite a los usuarios obtener un certificado de su participación.

## Arquitectura

El proyecto está construido sobre una arquitectura de microservicios orquestada con Docker. Un proxy inverso actúa como único punto de entrada, dirigiendo el tráfico al frontend o al microservicio correspondiente.

- **`reverse-proxy`**: Proxy inverso (Nginx) que actúa como la puerta de entrada a la aplicación. Expone un único puerto y redirige las peticiones al servicio correspondiente, permitiendo una comunicación fluida y segura entre el frontend y el backend.
- **`frontend`**: La aplicación de cara al usuario, construida con React y TypeScript.
- **`auth-service`**: Microservicio de autenticación (Java/Spring Boot). Gestiona la validación de usuarios y la generación de tokens JWT.
- **`voting-service`**: Microservicio de votación (Java/Spring Boot). Recibe los votos y los publica en una cola de RabbitMQ.
- **`ledger-service`**: Microservicio de registro (Java/Spring Boot). Escucha la cola de votos y los persiste en la base de datos.
- **`report-service`**: Microservicio de reportes (Java/Spring Boot). Genera resultados y certificados de votación.
- **`votaya-broker`**: Instancia de RabbitMQ que actúa como bus de mensajes.
- **`votaya-db`**: Base de datos PostgreSQL para persistir la información.

## Stack Tecnológico

- **Frontend**: React, TypeScript, Vite
- **Backend**: Java 17, Spring Boot 3
- **Base de Datos**: PostgreSQL
- **Mensajería**: RabbitMQ
- **Contenerización y Orquestación**: Docker, Docker Compose
- **Proxy Inverso**: Nginx

## Ejecución del Proyecto

Sigue estos pasos para ejecutar el proyecto en tu entorno local.

### Prerrequisitos

Asegúrate de tener instaladas las siguientes herramientas:
- [Docker](https://www.docker.com/get-started/)
- [Docker Compose](https://docs.docker.com/compose/install/) (generalmente incluido con Docker Desktop)

### Instrucciones de Ejecución Local

1.  Clona este repositorio en tu máquina local.
2.  Abre una terminal en el directorio raíz del proyecto.
3.  Ejecuta el siguiente comando para construir las imágenes y levantar todos los servicios en segundo plano:

    ```bash
    docker-compose up --build -d
    ```
4.  ¡Listo! La aplicación estará disponible en tu navegador en la siguiente URL:

    - **Aplicación VotaYa**: [http://localhost](http://localhost)

#### Puntos de Acceso Adicionales (para Desarrolladores)

- **RabbitMQ Management UI**: [http://localhost:15672](http://localhost:15672)
  - _Usuario_: `guest`
  - _Contraseña_: `guest`
- **Conexión a Base de Datos PostgreSQL**:
  - _Host_: `localhost`
  - _Puerto_: `5432`
  - _Usuario_: `votaya`
  - _Contraseña_: `votayapass`
  - _Base de datos_: `votayadb`

Para detener todos los servicios, ejecuta:
```bash
docker-compose down
```

## Despliegue en Render (Producción)

Este proyecto está pre-configurado para un despliegue sencillo en la plataforma en la nube **Render**.

1.  **Actualizar URL del Repositorio:**
    - Abre el archivo `render.yaml`.
    - Busca todas las apariciones de `https://github.com/tu_usuario/tu_repositorio.git` y reemplázalas con la URL de tu propio repositorio de GitHub.

2.  **Subir Cambios al Repositorio:**
    - Confirma todos los cambios y súbelos a tu repositorio Git.
      ```bash
      git add .
      git commit -m "feat: Prepare for Render deployment"
      git push
      ```

3.  **Crear el Proyecto en Render:**
    - Ve a tu [dashboard de Render](https://dashboard.render.com/).
    - Haz clic en **New** y selecciona **Blueprint**.
    - Conecta tu repositorio de GitHub y selecciona el repositorio del proyecto.
    - Render detectará automáticamente el archivo `render.yaml` y desplegará todos los servicios, la base de datos y el broker de mensajes.

Una vez finalizado el despliegue, Render te proporcionará la URL pública de tu aplicación.
