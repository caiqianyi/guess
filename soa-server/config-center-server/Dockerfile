FROM frolvlad/alpine-oraclejdk8:slim

COPY target/config-center-server-1.0.0.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]