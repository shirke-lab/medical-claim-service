# Use lightweight Java 17 image
FROM eclipse-temurin:17-jre

# Set working directory inside container
WORKDIR /app

# Copy WAR file into container
COPY target/Authentication-Service-0.0.1-SNAPSHOT.war app.war

# Expose port used by auth service
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.war"]
