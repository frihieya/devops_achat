FROM openjdk:17
EXPOSE 8082
ADD target/achat-backend-1.0.jar achat-backend.jar
ENTRYPOINT ["java","-jar","achat-backend.jar"]
