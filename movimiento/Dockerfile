FROM eclipse-temurin:17-jdk-alpine
LABEL description="MOVIMIENTO"
ADD target/movimiento-0.0.1-SNAPSHOT.jar app.jar
WORKDIR /app
EXPOSE 8083
ENTRYPOINT ["java","-jar","/app.jar"]