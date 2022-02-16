# Build
FROM maven:3.8.4-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
# add db to be tested
COPY sample.db /home/app/sample.db
RUN mvn -f /home/app/pom.xml clean package

# Run
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/Backend-1.0.0.jar /usr/local/lib/backend.jar
COPY sample.db /usr/local/lib/sample.db
WORKDIR /usr/local/lib
ENTRYPOINT ["java","-jar","backend.jar"]