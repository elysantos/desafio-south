# Build Stage for Spring boot application image
FROM openjdk:15-jdk-slim as build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x ./mvnw
# download the dependency if needed or if the pom file is changed
RUN ./mvnw dependency:go-offline -B

COPY src src

RUN ./mvnw package
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Production Stage for Spring boot application image
FROM openjdk:15-jdk-slim as production
ARG DEPENDENCY=/app/target/dependency

# Copy the dependency application file from build stage artifact
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Run the Spring boot application
ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-cp", "app:app/lib/*","com.elysantos.desafiosouth.DesafioSouthApplication"]
