# Java 25 real usando Eclipse Temurin (Adoptium)
FROM eclipse-temurin:25-jre

WORKDIR /app

# Ajusta el nombre si tu jar cambia de versión
ARG JAR_FILE=target/IntegrativeProject-0.0.1.jar
COPY ${JAR_FILE} app_integrative.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app_integrative.jar"]
