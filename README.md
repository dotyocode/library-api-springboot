# Library API

Uma API REST para gerenciamento de biblioteca desenvolvida com Spring Boot 3.5.4 e Java 21.

## 📋 Visão Geral

Esta API permite o gerenciamento completo de uma biblioteca, incluindo cadastro de autores, livros e usuários, com sistema de autenticação via OAuth2 (Google) e controle de acesso baseado em roles.

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.4**
- **Spring Data JPA**
- **Spring Security**
- **OAuth2 Client (Google)**
- **PostgreSQL 17**
- **Thymeleaf**
- **Lombok**
- **MapStruct**
- **Maven**
- **Docker & Docker Compose**

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

```markdown:README.md
<code_block_to_apply_changes_from>
```

src/main/java/io/github/dotyocode/libraryApi/
├── controller/ # Controladores REST
├── services/ # Lógica de negócio
├── repository/ # Camada de acesso a dados
├── model/
│ ├── entities/ # Entidades JPA
│ └── dto/ # Data Transfer Objects
├── mappers/ # Conversões entre entidades e DTOs
├── security/ # Configurações de segurança
├── validators/ # Validações customizadas
├── enums/ # Enumerações
└── common/ # Configurações e utilitários

````

## 📊 Modelo de Dados

### Entidades Principais

- **Usuario**: Gerencia usuários do sistema com roles de acesso
- **Autor**: Informações dos autores (nome, data de nascimento, nacionalidade)
- **Livro**: Dados dos livros (ISBN, título, data de publicação, gênero, preço)

### Gêneros Literários Suportados
- FICCAO
- FANTASIA
- MISTERIO
- ROMANCE
- BIOGRAFIA
- CIENCIA

## 🔐 Autenticação e Autorização

A API utiliza:
- **OAuth2 com Google**: Autenticação principal
- **Roles baseadas**: ADMIN e USER
- **Controle de acesso por endpoint**

### Permissões por Role

| Funcionalidade | ADMIN | USER |
|----------------|-------|------|
| Cadastrar Autor/Livro | ✅ | ❌ |
| Consultar Autor/Livro | ✅ | ✅ |
| Atualizar Autor/Livro | ✅ | ❌ |
| Deletar Autor/Livro | ✅ | ❌ |

## 🛠️ Configuração e Instalação

### Pré-requisitos

- Java 21
- Maven 3.6+
- Docker e Docker Compose
- Conta Google para OAuth2

### 1. Clone o repositório

```bash
git clone <repository-url>
cd libraryApi
````

### 2. Configure as variáveis de ambiente

Crie um arquivo `.env` ou edite o `application.yml`:

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: SEU_GOOGLE_CLIENT_ID
            client-secret: SEU_GOOGLE_CLIENT_SECRET
```

### 3. Inicie o banco de dados

```bash
docker-compose up -d
```

### 4. Execute a aplicação

```bash
./mvnw spring-boot:run
```

## 📚 Endpoints da API

### Autores

| Método | Endpoint        | Descrição                           | Permissão  |
| ------ | --------------- | ----------------------------------- | ---------- |
| GET    | `/autores`      | Lista autores com filtros opcionais | USER/ADMIN |
| GET    | `/autores/{id}` | Detalhes de um autor                | USER/ADMIN |
| POST   | `/autores`      | Cadastra novo autor                 | ADMIN      |
| PUT    | `/autores/{id}` | Atualiza autor                      | ADMIN      |
| DELETE | `/autores/{id}` | Remove autor                        | ADMIN      |

### Livros

| Método | Endpoint       | Descrição                          | Permissão  |
| ------ | -------------- | ---------------------------------- | ---------- |
| GET    | `/livros`      | Lista livros com filtros opcionais | USER/ADMIN |
| GET    | `/livros/{id}` | Detalhes de um livro               | USER/ADMIN |
| POST   | `/livros`      | Cadastra novo livro                | ADMIN      |
| PUT    | `/livros/{id}` | Atualiza livro                     | ADMIN      |
| DELETE | `/livros/{id}` | Remove livro                       | ADMIN      |

### Filtros de Pesquisa - Livros

- `isbn`: Busca por ISBN
- `titulo`: Busca por título
- `nome-autor`: Busca por nome do autor
- `genero`: Filtro por gênero
- `ano-publicacao`: Filtro por ano de publicação
- `pagina`: Número da página (padrão: 0)
- `tamanho-pagina`: Itens por página (padrão: 10)

### Filtros de Pesquisa - Autores

- `nome`: Busca por nome
- `nascionalidade`: Filtro por nacionalidade

## 🔧 Configuração do Banco de Dados

### PostgreSQL via Docker

O projeto inclui um `docker-compose.yml` configurado:

```yaml
services:
  postgres:
    image: postgres:17
    container_name: libraryApi
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 123456
      POSTGRES_DB: library
    ports:
      - "5432:5432"
```

### Configuração JPA

- DDL Auto: `update`
- Show SQL: `true`
- Format SQL: `true`

## 🧪 Testes

Execute os testes com:

```bash
./mvnw test
```

## 📦 Build e Deploy

### Build da aplicação

```bash
./mvnw clean package
```

### Executar JAR

```bash
java -jar target/libraryApi-0.0.1-SNAPSHOT.jar
```

## 🔍 Monitoramento

A aplicação possui auditoria automática com:

- Data de criação
- Data de última modificação
- Rastreamento de usuário responsável pelas operações

```

```
