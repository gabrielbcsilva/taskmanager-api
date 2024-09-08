FROM openjdk:17-jdk-slim

WORKDIR /app

COPY .mvn/ .mvn

COPY mvnw pom.xml ./

EXPOSE 8080

RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
