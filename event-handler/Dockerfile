FROM maven:3.9-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY . /app/
RUN mvn clean package -pl event-handler -am -DskipTests

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/event-handler/target/event-handler-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
