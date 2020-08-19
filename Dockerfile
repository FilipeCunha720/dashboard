FROM openjdk:11-slim
WORKDIR /app
COPY build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","app.jar","/app.jar"]