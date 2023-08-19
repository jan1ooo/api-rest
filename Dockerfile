FROM openjdk:17
VOLUME /tmp
ADD ./target/api-rest-0.0.1-SNAPSHOT.jar api-rest.jar
ENTRYPOINT ["java","-jar","/api-rest.jar"]