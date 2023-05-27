FROM maven:3.8.2-openjdk-17

WORKDIR /app

#COPY . .
COPY .mvn/ .mvn
COPY mvnw mvnw.cmd pom.xml ./
COPY src ./src

EXPOSE 8080

CMD mvn spring-boot:run