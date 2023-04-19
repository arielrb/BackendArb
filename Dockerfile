FROM amazoncorretto:11-alpine-jdk
MAINTAINER arielrb
COPY target/BackendArb.jar BackendArb.jar
ENTRYPOINT ["java","-jar","/BackendArb.jar"]