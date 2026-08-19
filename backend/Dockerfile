FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY backend/ .
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon -x test
RUN find infrastructure/build/libs -name "*.jar" ! -name "*-plain.jar" -exec cp {} /app/app.jar \;

FROM eclipse-temurin:21-jre-alpine
WORKDIR /home/app
COPY --from=builder /app/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]