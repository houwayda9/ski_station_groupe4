FROM openjdk:11
EXPOSE 8089
COPY target/ski-station-app.jar ski-station-app.jar
ENTRYPOINT ["java","-jar","/ski-station-app.jar"]
