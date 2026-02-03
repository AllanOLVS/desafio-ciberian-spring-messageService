# desafio-ciberian-mensagem-service

API para gerenciar mensagens WhatsApp

## Requisitos

- Java 21
- PostgreSQL
- Maven
- Spring Boot 3

## Config

Antes de rodar, configure usuario e senha do postgreSQL no application.properties, pra se conectar ao seu db local:

## Endpoints

- **POST** `/api/auth/register` - Criar usuário
- **POST** `/api/auth/login` - Fazer login (retorna o JWT)
- **GET** `/api/mensagens` - Listar mensagens
- **POST** `/api/mensagens` - Criar mensagem
- **GET** `/api/mensagens/{id}` - Buscar por ID
- **PUT** `/api/mensagens/{id}` - Atualizar
- **DELETE** `/api/mensagens/{id}` - Deletar

## Swagger
Link do swagger (Abrir depois de rodar o projeto): http://localhost:8080/swagger-ui.html
