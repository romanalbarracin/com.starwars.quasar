FROM openjdk:latest

ADD target/com.starwars.quasar-0.0.3.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
