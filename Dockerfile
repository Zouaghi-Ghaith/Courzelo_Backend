FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/corzello-1.0.jar corzello-1.0.jar
ENTRYPOINT ["java","-jar","/corzello-1.0.jar"]
