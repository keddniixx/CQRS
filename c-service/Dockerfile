FROM maven:3.9-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY pom.xml /app/
COPY c-service /app/c-service
RUN cd /app/c-service && mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/c-service/target/c-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 5555
ENTRYPOINT ["java", "-jar", "app.jar"]
