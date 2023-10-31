FROM amazoncorretto:11-al2023-jdk

RUN mkdir -p /var/lib/app

WORKDIR /var/lib/app

EXPOSE 8080

COPY target/orderingsystem-0.0.1-SNAPSHOT.jar /var/lib/app/

CMD ["java", "-jar", "orderingsystem-0.0.1-SNAPSHOT.jar"]
