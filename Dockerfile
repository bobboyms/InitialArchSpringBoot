FROM docker:20.10.3-dind
#USER root
#FROM alpine:3.12.3
#RUN apk update
#RUN apk add docker
RUN apk add docker-compose
#RUN apk fetch openjdk11
#RUN apk add openjdk11
RUN apk add bash
#RUN apk add wget
#RUN apk add unzip
#ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk
#ENV PATH="$JAVA_HOME/bin:${PATH}"

WORKDIR /home/workdir
#RUN mkdir /opt/gradle
#RUN wget https://services.gradle.org/distributions/gradle-6.8.2-all.zip
#RUN unzip gradle-6.8.2-all.zip
#ENV PATH=$PATH:/home/gradle/src/gradle-6.8.2/bin
COPY . /home/workdir
COPY ./docker/Dockerfile Dockerfile
#COPY ./docker/startimage.sh startimage.sh
#COPY ./docker/script.sh script.sh
#RUN gradle build --debug - -x test
ENTRYPOINT ["bash", "./docker/startimage.sh"]


