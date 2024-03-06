FROM eclipse-temurin:17-jdk-alpine
LABEL description="MOVIMIENTO"
ADD target/gateway-0.0.1-SNAPSHOT.jar app.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]