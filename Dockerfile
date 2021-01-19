FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./service/sr-articles/target/sr-articles-0.0.1-SNAPSHOT.jar sr-articles-0.0.1-SNAPSHOT.jar
COPY ./service/user-center/target/user-center-0.0.1-SNAPSHOT.jar user-center-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/sr-articles-0.0.1-SNAPSHOT.jar.jar", "&"]
ENTRYPOINT ["java","-jar","/user-center-0.0.1-SNAPSHOT.jar", "&"]