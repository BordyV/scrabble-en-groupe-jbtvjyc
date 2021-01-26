FROM openjdk:11-jdk-slim
ARG JAR_FILE=/target/client-0.0.1.jar
ENV PORT="8081"
ENV NOM="Mopolo le Roi des Mots"
ENV AUTOCONNECT="FALSE"
ENV SERVERIP="localhost"
ENV OWNIP="localhost"
COPY ${JAR_FILE} client.jar
ENTRYPOINT java -jar /client.jar