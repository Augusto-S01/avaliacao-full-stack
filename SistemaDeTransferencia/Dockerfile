# Etapa 1: Imagem base com Maven e JDK
FROM maven:3.8.1-openjdk-17 AS build

# Copia os arquivos do projeto para o container
COPY SistemaDeTransferencia /usr/src/app

# Define o diretório de trabalho
WORKDIR /usr/src/app

# Constrói o projeto com Maven
RUN mvn clean package

# Etapa 2: Imagem base com apenas JDK
FROM eclipse-temurin:17-jdk-alpine

# Copia o arquivo jar do container de build para o novo container
COPY --from=build /usr/src/app/target/*.jar app.jar

# Define o comando para iniciar o aplicativo
ENTRYPOINT ["java","-jar","/app.jar"]