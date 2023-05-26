FROM maven as builder
WORKDIR /build
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
