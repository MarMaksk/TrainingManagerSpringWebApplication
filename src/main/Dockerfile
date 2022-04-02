FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app-0.0.1.jar
ENTRYPOINT ["java","-jar","app-0.0.1.jar"]