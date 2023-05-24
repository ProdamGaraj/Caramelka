FROM maven:3.8.2-openjdk-17 AS build

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw mvnw.cmd pom.xml ./
COPY src ./src
RUN mvn -f pom.xml clean package

FROM openjdk:17
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]