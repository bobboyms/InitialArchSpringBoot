FROM gradle:6.7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --debug -x test
#RUN gradle build --no-daemon

FROM openjdk:11-jre-slim
COPY --from=build /home/gradle/src/build/libs/*.jar application.jar
COPY ./script.sh script.sh
EXPOSE 8090
ENTRYPOINT ["bash", "script.sh"]
