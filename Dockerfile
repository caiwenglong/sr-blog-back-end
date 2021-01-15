FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/siriblog.jar siriblog.jar
ENTRYPOINT ["java","-jar","/siriblog.jar", "&"]