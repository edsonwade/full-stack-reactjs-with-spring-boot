#########################################
# Packaged spring boot app using maven
#########################################
#FROM openjdk:16-jdk-alpine as as builder
# Use the official OpenJDK image for Java 17
# Use the official OpenJDK image for Java 17
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/backend-0.0.1-SNAPSHOT.jar /app/backend.jar

# Specify the command to run your application
CMD ["java", "-jar", "backend.jar"]
