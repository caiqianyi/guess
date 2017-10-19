FROM frolvlad/alpine-oraclejdk8:slim

COPY target/service-eureka-server-1.0.0.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]