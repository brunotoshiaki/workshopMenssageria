FROM maven:3.8.7-eclipse-temurin-19-alpine as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


FROM eclipse-temurin:19-alpine
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar

ARG RABBITMQ_SERVER=rabbitmq-host
ARG EUREKA_SERVER=localhost

ENTRYPOINT ENTRYPOINT java -jar -Dspring.profiles.active=production app.jar