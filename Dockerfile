# Etapa 1: Construir o projeto com Maven
FROM openjdk:17-slim AS build

# Instalar o Maven
RUN apt-get update && apt-get install -y maven

WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem final com o JAR
FROM openjdk:17-slim

WORKDIR /app
COPY --from=build /app/target/order-service-0.0.1-SNAPSHOT.jar /app/order-service-0.0.1-SNAPSHOT.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "order-service-0.0.1-SNAPSHOT.jar"]
