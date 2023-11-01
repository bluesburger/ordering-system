# Build Step
FROM maven:3.8.7-openjdk-18-slim as build

WORKDIR /app

COPY pom.xml .

COPY src/ src/

RUN mvn -f pom.xml -DskipTests clean package

# Application Step
FROM amazoncorretto:11-al2023-jdk

RUN mkdir -p /var/lib/app

WORKDIR /var/lib/app

EXPOSE 8080

COPY --from=build /app/target/*.jar orderingsystem-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "orderingsystem-0.0.1-SNAPSHOT.jar"]
