FROM gradle:7.4.2-jdk11-alpine AS build
COPY . /home/gradle/src
WORKDIR /home/gradle/src
COPY build.gradle settings.gradle /home/gradle/src/
RUN gradle build --no-daemon -x test

FROM registry.access.redhat.com/ubi8/openjdk-11:1.11

USER root
RUN mkdir /app

COPY --from=build /home/gradle/src/build/quarkus-app/quarkus-run.jar /app/quarkus-run.jar

COPY --chown=185 build/quarkus-app/lib/ /deployments/lib/
COPY --chown=185 build/quarkus-app/*.jar /deployments/
COPY --chown=185 build/quarkus-app/app/ /deployments/app/
COPY --chown=185 build/quarkus-app/quarkus/ /deployments/quarkus/
EXPOSE 8081
ENV AB_JOLOKIA_OFF=""
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

