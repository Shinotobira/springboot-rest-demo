FROM maven:3-openjdk-17-slim

WORKDIR /app

COPY . .

RUN mvn clean package

CMD ["java", "-jar","-Dspring.profiles.active=dev","-Dspring.config.location=springboot-rest-demo-config/src/main/resources/springboot-rest-demo.yml", "springboot-rest-demo-ws/target/springboot-rest-demo-ws-1.0.0-SNAPSHOT.jar"]