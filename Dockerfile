FROM maven:3.9.6-eclipse-temurin-17
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests
ENTRYPOINT ["java", "-jar", "target/hotel-platform-0.0.1-SNAPSHOT.jar"]




