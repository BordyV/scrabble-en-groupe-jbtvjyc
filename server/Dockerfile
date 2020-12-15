FROM openjdk:11-jdk-slim
ARG JAR_FILE=/target/server-0.0.1.jar
COPY ${JAR_FILE} server.jar
ENTRYPOINT java -jar /server.jar