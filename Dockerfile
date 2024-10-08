# Use a base image with OpenJDK 17
FROM openjdk:17-jdk-slim

# Set the JAR file path as an argument
ARG JAR_FILE=target/*.jar

# Copy the JAR file into the Docker image
COPY ${JAR_FILE} app.jar

# Define the command to run the JAR file
ENTRYPOINT ["java", "-jar", "/app.jar"]
