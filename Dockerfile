FROM openjdk:17
EXPOSE 8082
ADD target/Achat-backend-1.0.jar Achat-backend.jar
ENTRYPOINT ["java","-jar","Achat-backend.jar"]