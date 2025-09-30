# Gestão Financeira API

Uma API RESTful completa para gestão de finanças pessoais, construída com **Spring Boot**, que permite que usuários registrem receitas, despesas, gerenciem categorias e obtenham relatórios financeiros.

---

## 💡 Funcionalidades Principais

* **Autenticação segura** com JWT para proteger endpoints sensíveis
* **Gestão de categorias**: criar, listar, editar e excluir categorias (ex: Alimentação, Transporte, Lazer)
* **Registro de transações**: entradas (receitas) e saídas (despesas), associadas a categorias
* **Relatórios financeiros**: balanço consolidado com valores de receitas, despesas e saldo
* **Documentação interativa** via Swagger / OpenAPI

---

## 🛠 Tecnologias Utilizadas

* Java 17
* Spring Boot 3
* Spring Security (autenticação JWT)
* PostgreSQL
* JPA / Hibernate
* Maven
* Docker & Docker Compose
* SpringDoc OpenAPI / Swagger
* ModelMapper

---

## 🔍 Pré-requisitos

* Java 17 (JDK)
* Maven
* Docker
* Docker Compose

---

## 🚀 Como executar

### 1. Construir o projeto

```bash
mvn clean package
```

### 2. Iniciar com Docker Compose

```bash
docker-compose up --build
```

Isso criará dois containers:

* **app**: a API Spring Boot (`http://localhost:8080`)
* **db**: PostgreSQL (porta `5433` local mapeada para `5432` do container)

---

## 📄 Documentação / Swagger

Após subir os containers:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 🔐 Autenticação & Fluxo de Uso

1. **Registro de usuário**
   `POST /api/auth/register`
   Envie `username`, `email`, `password`.

2. **Login / Token JWT**
   `POST /api/auth/login`
   Retorna token JWT.

3. **Autorizar no Swagger**
   Clique em **Authorize** e insira `Bearer <seu-token>`.

---

## 🗂 Endpoints Principais

| Método   | Caminho                  | Descrição                    |
| -------- | ------------------------ | ---------------------------- |
| `POST`   | `/api/auth/register`     | Criar novo usuário           |
| `POST`   | `/api/auth/login`        | Autenticar e obter token JWT |
| `GET`    | `/api/categories`        | Listar categorias            |
| `POST`   | `/api/categories`        | Criar nova categoria         |
| `PUT`    | `/api/categories/{id}`   | Atualizar categoria          |
| `DELETE` | `/api/categories/{id}`   | Excluir categoria            |
| `GET`    | `/api/transactions`      | Listar transações            |
| `POST`   | `/api/transactions`      | Criar transação              |
| `PUT`    | `/api/transactions/{id}` | Atualizar transação          |
| `DELETE` | `/api/transactions/{id}` | Excluir transação            |
| `GET`    | `/api/reports/balance`   | Obter balanço financeiro     |

---

## 🧪 Testes

```bash
mvn test
```

---

## 🧰 Estrutura & Contribuição

* Código-fonte: `src/main/java`
* Configurações de segurança e mapeamentos em pacotes específicos
* Contribuições são bem-vindas! Abra uma *issue* ou *pull request*.

---

## 🙋 Autor

Projeto desenvolvido por **PedroPsy**
Contribua via [issues](https://github.com/PedroPsy/Gestao-Financeira/issues) no repositório.

---
