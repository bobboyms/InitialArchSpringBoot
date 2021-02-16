#FROM ubuntu:20.10 as build
#RUN apt update
#RUN apt install -y apt-transport-https ca-certificates curl software-properties-common
#RUN curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
#RUN add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu focal stable"
#RUN apt update
#RUN apt-cache policy docker-ce
#RUN apt install docker-ce

#FROM gradle:6.7-jdk11 AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle build --debug
# -x test
#RUN gradle build --no-daemon

#FROM docker:19.03.0-dind AS build
#USER root
FROM alpine:3.12.3
RUN apk update
RUN apk add docker
RUN apk add docker-compose
#RUN apk fetch openjdk11
#RUN apk add openjdk11
#RUN apk add bash
#RUN apk add wget
#RUN apk add unzip
#ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk
#ENV PATH="$JAVA_HOME/bin:${PATH}"

WORKDIR /home/gradle/src
#RUN mkdir /opt/gradle
#RUN wget https://services.gradle.org/distributions/gradle-6.8.2-all.zip
#RUN unzip gradle-6.8.2-all.zip
ENV PATH=$PATH:/home/gradle/src/gradle-6.8.2/bin
COPY . /home/gradle/src
COPY ./docker/Dockerfile Dockerfile
#COPY ./docker/script.sh script.sh
#RUN gradle build --debug - -x test


#RUN ./gradlew build --debug

#FROM openjdk:11-jre-slim
#COPY --from=build /home/gradle/src/build/libs/*.jar application.jar
#COPY docker/script.sh script.sh
#EXPOSE 8090
#ENTRYPOINT ["bash", "script.sh"]

