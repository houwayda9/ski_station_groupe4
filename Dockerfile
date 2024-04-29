FROM openjdk:11
EXPOSE 8088
COPY target/ski-app.jar ski-app.jar
ENTRYPOINT ["java","-jar","/ski-app.jar"]

