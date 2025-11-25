# Etapa 1: Compilación de la aplicación con Maven
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app

# Copiar el pom.xml para descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el resto del código fuente y compilar
COPY src ./src
RUN mvn clean install -DskipTests

# Etapa 2: Creación de la imagen final con la JRE
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Exponer el puerto en el que correrá el servicio
EXPOSE 8083

# Copiar el JAR compilado desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Comando de entrada para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
