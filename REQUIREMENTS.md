# Requisitos del Sistema - VotaYa

Este documento detalla los requisitos funcionales y no funcionales de la plataforma de votación digital VotaYa.

## Requisitos Funcionales (RF)

### RF-01: Autenticación de Usuario
- El sistema debe permitir a los usuarios autenticarse utilizando un método seguro que simula el uso de un certificado digital (FirmaEC).
- Tras una autenticación exitosa, el sistema debe generar un token de sesión (JWT) que autorice al usuario a realizar acciones posteriores.

### RF-02: Emisión de Voto
- Un usuario autenticado debe poder emitir un único voto para una elección específica.
- El voto debe contener la información del candidato o la opción seleccionada.
- El sistema debe confirmar la recepción del voto para su procesamiento.

### RF-03: Generación de Certificado de Votación
- Un usuario que ha emitido su voto debe poder obtener un certificado digital (en formato DTO/JSON) que confirme su participación.
- El certificado debe ser único para cada votante y estar asociado a su cédula.

### RF-04: Consulta de Resultados Agregados
- El sistema debe proveer un endpoint público o restringido para consultar los resultados agregados de la votación en tiempo real.
- Los resultados deben mostrar el conteo de votos para cada candidato u opción sin revelar la identidad de los votantes.

## Requisitos No Funcionales (RNF)

### RNF-01: Seguridad
- **Autenticación**: Toda la comunicación entre el frontend y los servicios de backend que requieran datos de usuario debe estar protegida y requerir un token JWT válido.
- **Integridad del Voto**: El sistema debe garantizar que un voto no pueda ser alterado una vez emitido y registrado.

### RNF-02: Asincronismo y Desacoplamiento
- La emisión del voto y su registro deben ser procesos asíncronos para garantizar que la plataforma pueda manejar picos de carga.
- Los microservicios deben estar desacoplados, comunicándose a través de un bus de mensajes (RabbitMQ).

### RNF-03: Idempotencia
- El sistema debe prevenir que un mismo voto sea procesado más de una vez, incluso si el usuario envía la misma solicitud múltiples veces. Esto se logrará mediante una clave de idempotencia (`Idempotency-Key`).

### RNF-04: Auditabilidad
- El sistema debe mantener un registro (ledger) de todos los votos emitidos.
- Este registro debe contener la información del votante (cédula), la carga útil del voto y una marca de tiempo.

### RNF-05: Escalabilidad
- La arquitectura de microservicios debe permitir escalar cada servicio de forma independiente según la demanda.

### RNF-06: Portabilidad
- Toda la aplicación debe estar containerizada usando Docker, permitiendo que se ejecute de manera consistente en cualquier entorno que soporte Docker.
