FROM openjdk:25-jdk-slim
ARG JAR_FILE=target/IntegrativeProject-0.0.1.jar
COPY ${JAR_FILE} app_integrative.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_integrative.jar"]