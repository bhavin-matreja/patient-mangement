FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

# go-offline -> cache the dependencies so that next time the container starts faster
RUN mvn dependency:go-offline -B

COPY src ./src

# skips tests to save time and avoid downloading test libraries.
RUN mvn clean package -DskipTests

# Using JRE instead of JDK to decrease container size for dev
FROM eclipse-temurin:21-jre-alpine as runner
# FROM openjdk:21-jdk AS runner

WORKDIR /app

COPY --from=builder ./app/target/auth-service-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 4005

ENTRYPOINT ["java", "-jar", "app.jar"]
