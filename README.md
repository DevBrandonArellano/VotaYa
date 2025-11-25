# VotaYa - Plataforma de Votación Digital

Bienvenido a VotaYa, una plataforma de votación digital segura y confiable. Este proyecto implementa un sistema de votación electrónica basado en una arquitectura de microservicios, diseñado para garantizar la integridad, seguridad y auditabilidad de cada voto.

## Características Principales

- **Autenticación Segura**: Utiliza un mecanismo de autenticación basado en certificados digitales (simulando FirmaEC de Ecuador) para garantizar que solo los votantes autorizados puedan emitir su voto.
- **Emisión de Voto Asíncrona**: El sistema procesa los votos de manera asíncrona para asegurar una alta disponibilidad y una experiencia de usuario fluida, incluso bajo alta carga.
- **Registro Inmutable de Votos**: Cada voto es registrado en un libro contable (ledger) para futuras auditorías y para garantizar la integridad del proceso.
- **Resultados en Tiempo Real**: Provee resultados agregados que se pueden consultar en cualquier momento.
- **Certificado de Votación**: Permite a los usuarios descargar un certificado que prueba que han participado en el proceso de votación.
- **Idempotencia**: Previene la duplicación de votos mediante el uso de claves de idempotencia.

## Arquitectura

El proyecto está construido sobre una arquitectura de microservicios orquestada con Docker Compose.

- **`frontend`**: La aplicación de cara al usuario, construida con React y TypeScript. Proporciona la interfaz para que los usuarios se autentiquen, emitan su voto y consulten resultados.
- **`auth-service`**: Microservicio de autenticación (Java/Spring Boot). Gestiona el login de los usuarios y la generación de tokens JWT.
- **`voting-service`**: Microservicio de votación (Java/Spring Boot). Recibe los votos de los usuarios y los publica en una cola de RabbitMQ para su procesamiento asíncrono.
- **`ledger-service`**: Microservicio de registro (Java/Spring Boot). Escucha la cola de votos y los persiste en una base de datos SQLite, creando un registro de auditoría.
- **`report-service`**: Microservicio de reportes (Java/Spring Boot). Lee la base de datos de votos para generar resultados agregados y certificados de votación individuales.
- **`votaya-broker`**: Instancia de RabbitMQ que actúa como bus de mensajes entre los microservicios.

## Stack Tecnológico

- **Frontend**: React, TypeScript, Vite
- **Backend**: Java 17, Spring Boot 3
- **Base de Datos**: SQLite (para el ledger de votos)
- **Mensajería**: RabbitMQ
- **Contenerización**: Docker, Docker Compose

## Cómo Empezar

Para levantar el entorno completo, asegúrate de tener Docker y Docker Compose instalados y ejecuta el siguiente comando en la raíz del proyecto:

```bash
docker-compose up --build
```

Los servicios estarán disponibles en los siguientes puertos:

- **Frontend**: `http://localhost:80`
- **Auth Service**: `http://localhost:8081`
- **Voting Service**: `http://localhost:8082`
- **Ledger Service**: `http://localhost:8083`
- **Report Service**: `http://localhost:8084`
- **RabbitMQ Management**: `http://localhost:15672`
