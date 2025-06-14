# Fase 1: Construir el JAR con Maven
FROM maven:3.8.6-openjdk-17 AS builder
WORKDIR /app
# Copia solo el pom.xml primero (para cachear dependencias)
COPY pom.xml .
# Descarga dependencias (cache si el pom no cambia)
RUN mvn dependency:go-offline -B
# Copia el código fuente
COPY src ./src
# Compila el proyecto y genera el JAR (omite tests)
RUN mvn clean package -DskipTests

# Fase 2: Imagen final mínima
FROM openjdk:17-jdk-slim
WORKDIR /app
# Copia el JAR desde la fase de construcción
COPY --from=builder /app/target/*.jar app.jar
# Puerto que expone Spring Boot (ajusta si usas otro en application.properties)
EXPOSE 8080
# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]