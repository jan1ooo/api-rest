# Itaú Unibanco - Desafio de Programação
[Detalhes do desafio no repositório oficial](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior)


## Comandos utilizados para rodar no Docker
Baixar imagem do Dockerhub
```
docker pull openjdk:17-alpine
```
API-REST Dockerfile
```
FROM openjdk:17
VOLUME /tmp
ADD ./target/api-rest-0.0.1-SNAPSHOT.jar api-rest.jar
ENTRYPOINT ["java","-jar","/api-rest.jar"]
``` 
Criar uma rede Docker
```
docker network create api-rest
```
Rodar aplicação
```
mvnw clean package

docker build -t api-rest:v1 .

docker run -p 8080:8080 --name api-rest --network api-rest api-rest:v1
```

Log da aplicação

![logger](https://user-images.githubusercontent.com/95763551/261866784-4af044aa-b61b-43e4-a3da-5c1b6c9aef27.jpg)
