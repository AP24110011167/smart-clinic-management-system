
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN chmod +x mvnw 2>/dev/null || true
RUN mvn -B clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/smart-clinic-1.0.0.jar"]
