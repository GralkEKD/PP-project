FROM openjdk:21
COPY demo-0.0.1-SNAPSHOT-jar-with-dependencies.jar app/app.jar
COPY queries.properties app/queries.properties
CMD ["java", "-jar", "app/app.jar"]
EXPOSE 80