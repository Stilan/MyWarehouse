FROM openjdk:17-alpine
RUN mkdir /app
COPY ./target/MyWarehouse-0.0.1-SNAPSHOT.jar /app/MyWarehouse.jar
WORKDIR /app
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "MyWarehouse.jar"]
