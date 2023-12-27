# Usando a imagem do Maven para compilar o aplicativo
FROM maven:3.8-jdk-11 AS builder

# Definindo o diretório de trabalho no contêiner
WORKDIR /app

# Copiando o arquivo de definição de projeto e os arquivos de código-fonte
COPY pom.xml .
COPY src ./src

# Compilando o aplicativo e gerando o arquivo JAR
RUN mvn -DskipTests clean package

# Usando a imagem do Amazon Corretto para executar o aplicativo
FROM amazoncorretto:11-al2023-jdk

WORKDIR /app

# Copie o JAR gerado a partir da etapa anterior para o contêiner
COPY --from=builder /app/target/orderingsystem-0.0.1-SNAPSHOT.jar .

# Expondo a porta que o aplicativo está ouvindo
EXPOSE 8080

# Comando para iniciar o aplicativo
CMD ["java", "-jar", "orderingsystem-0.0.1-SNAPSHOT.jar"]