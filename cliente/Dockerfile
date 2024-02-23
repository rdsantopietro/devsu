FROM eclipse-temurin:17-jdk-alpine
LABEL description="CLIENTE"
ADD target/cliente-0.0.1-SNAPSHOT.jar app.jar
WORKDIR /app
ARG CLIENTE_SERVICE_PORT=8081
ENV PORT=${CLIENTE_SERVICE_PORT}
EXPOSE ${PORT}
ENTRYPOINT ["java","-jar","/app.jar"]
