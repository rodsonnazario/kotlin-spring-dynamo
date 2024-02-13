# Kotlin com Spring Boot e DynamoDB.

Projeto demonstrando o uso do Kotlin com Spring Boot e persistência no DynamoDB.

## Começando

1. Inicie o serviço local do DynamoDB através da localstack, 
disponível em [docker-compose.yml](local%2Flocalstack%2Fdocker-compose.yml).

    ```docker-compose up -d```
2. Utilize a [collection](local%2Fcollection%2FInsomnia_2024-02-12.json) para fazer requisições de POST (save), GET (read) e DELETE.

## Dependências Utilizadas

- Spring Boot Starter Web
- Kotlin Reflect/Stdlib
- DynamoDB-Enhanced
