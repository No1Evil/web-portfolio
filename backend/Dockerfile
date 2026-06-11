FROM amazoncorretto:21-alpine-jdk
WORKDIR /app
COPY build/libs/backend.jar ./app.jar
CMD ["java", "-jar", "app.jar", "nogui"]
