FROM maven:3.9.6 AS build
WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn --version
RUN mvn -B -DskipTests clean package

FROM eclipse-temurin:19-jdk

COPY --from=build /app/target/tinyurl-0.0.1-SNAPSHOT.jar /app/tinyurl.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "/app/tinyurl.jar"]
