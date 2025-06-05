FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/*.jar /app/notificationservice.jar

EXPOSE 8086

ENTRYPOINT ["java","-jar","notificationservice.jar"]

