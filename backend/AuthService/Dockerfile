FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Esta línea es la que genera el código nuevo
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
# Esta línea copia el resultado de la compilación anterior
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]