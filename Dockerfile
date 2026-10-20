FROM eclipse-temurin:25-jre

WORKDIR /app

COPY app/target/*.jar app.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app.jar"]