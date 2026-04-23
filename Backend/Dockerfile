# Estágio 1: Build da aplicação
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
# O COPY pega os arquivos do seu computador físico e os copia para dentro da imagem do Docker
COPY pom.xml .
COPY src ./src

# Compila o projeto ignorando os testes para ser mais rápido
RUN mvn clean package -DskipTests

# Estágio 2: Execução
# Iniciamos uma segunda imagem base do zero.
# O sufixo alpine indica que é uma versão super minimalista do Linux, contendo apenas o Java 25
FROM eclipse-temurin:25-jdk-alpine
WORKDIR /app

# Copia o .jar gerado no Estágio 1 (note o nome do artefato baseado no seu pom.xml)
COPY --from=build /app/target/diocese-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]