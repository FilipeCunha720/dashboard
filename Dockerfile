FROM openjdk:14.0.1-slim
WORKDIR /app
COPY build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","app.jar","/app.jar"]