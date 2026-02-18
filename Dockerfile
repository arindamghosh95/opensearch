FROM gradle:8.14.1-jdk17 AS build
COPY --chown=gradle::gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon

From eclipse-temurin:17-jdk
EXPOSE 8080
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "/app/app.jar"]