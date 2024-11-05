FROM openjdk:17-jdk-alpine
EXPOSE 8089
ADD target/Corzello-5.0.0.jar Corzello-5.0.0.jar
ENTRYPOINT ["java","-jar","/Corzello-5.0.0.jar"]
