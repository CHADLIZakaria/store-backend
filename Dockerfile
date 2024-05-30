FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean pakcge -DskipTests
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/*.jar demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]