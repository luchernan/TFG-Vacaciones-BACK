# Fase 1: Construir el JAR con Maven
FROM maven:3.8.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
# Añade estas opciones para evitar problemas comunes
RUN mvn clean package -DskipTests -Dmaven.test.skip=true -Dmaven.main.skip=true -Dmaven.source.skip=true

# Fase 2: Imagen final mínima
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]