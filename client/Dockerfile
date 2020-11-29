FROM openjdk:11-jdk-slim
ARG JAR_FILE=/target/client-0.0.1.jar
COPY ${JAR_FILE} client.jar
ENTRYPOINT java -jar /client.jar