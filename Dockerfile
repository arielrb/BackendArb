FROM amazoncorretto:11-alpine-jdk
MAINTAINER arielrb
COPY target/PortfolioAriel-0.0.1-SNAPSHOT.jar BackendArb.jar
ENTRYPOINT ["java","-jar","/BackendArb.jar"]
EXPOSE 8080