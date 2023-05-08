FROM eclipse-temurin:19.0.2_7-jdk-jammy

WORKDIR /app
COPY . /app
RUN ./gradlew --no-daemon test
