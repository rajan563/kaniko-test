FROM openjdk:11
EXPOSE 8080
ADD fiata-api/target/fiata-api-1.1.0.jar fiata-api-1.1.0.jar
ENTRYPOINT ["java","-jar","/fiata-api-1.1.0.jar"]