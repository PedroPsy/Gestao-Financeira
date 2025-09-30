## API de Gestão Financeira
# Uma API RESTful completa, desenvolvida com Spring Boot, para a gestão de finanças pessoais. A aplicação permite que os utilizadores se registem, autentiquem e controlem as suas transações financeiras (entradas e saídas), organizando-as por categorias e visualizando relatórios de balanço.

Funcionalidades
🔐 Autenticação Segura: Sistema de registo e login com autenticação baseada em JWT (JSON Web Tokens) para proteger os endpoints da API.

🗂️ Gestão de Categorias: Crie e gira categorias personalizadas para classificar as suas transações (ex: Alimentação, Transporte, Lazer).

💰 Registo de Transações: Adicione transações de Entrada (receitas) e Saída (despesas), associando-as a uma categoria.

📊 Relatórios Financeiros: Obtenha um balanço financeiro detalhado com o total de receitas, despesas e o saldo consolidado.

📄 Documentação Interativa: A API está documentada com Swagger (OpenAPI), permitindo explorar e testar todos os endpoints de forma fácil e interativa.

Tecnologias Utilizadas
Java 17

Spring Boot 3

Spring Security (com autenticação JWT)

PostgreSQL como banco de dados principal

JPA (Hibernate) para persistência de dados

Maven para gestão de dependências

Docker e Docker Compose para containerização

Swagger (SpringDoc OpenAPI) para documentação da API

ModelMapper para mapeamento de DTOs

Pré-requisitos
Para executar este projeto, vai precisar de ter o seguinte software instalado na sua máquina:

Docker

Docker Compose

Maven

Java 17 (JDK)

Como Executar a Aplicação (Método Recomendado)
A maneira mais simples de executar a aplicação é utilizando Docker, que irá configurar tanto a API quanto o banco de dados PostgreSQL automaticamente.

1. Construir o Projeto
   Antes de iniciar os contentores, é necessário compilar o projeto e gerar o ficheiro .jar executável. Navegue até à pasta raiz do projeto e execute o seguinte comando:

Bash

mvn clean package
Este comando irá compilar o código, executar os testes e criar o ficheiro finances-0.0.1-SNAPSHOT.jar dentro da pasta target/.

2. Iniciar os Contentores com Docker Compose
   Com o projeto já construído, utilize o Docker Compose para iniciar a API e o banco de dados. Na raiz do projeto, execute o comando:

Bash

docker-compose up --build
O comando --build garante que a imagem Docker da sua aplicação é reconstruída com a versão mais recente do código. Após a execução, terá dois contentores em funcionamento:

app: A sua API Spring Boot, que estará acessível em http://localhost:8080.

db: O banco de dados PostgreSQL. A porta 5433 da sua máquina estará ligada à porta 5432 do contentor, permitindo que se conecte com uma ferramenta de base de dados externa, se desejar (como o DBeaver ou DataGrip).

A aplicação estará pronta para ser utilizada quando vir nos logs a mensagem "Started FinanceApplication".

Como Utilizar a API
Documentação Interativa (Swagger)
A forma mais fácil de explorar e interagir com a API é através da interface do Swagger. Após iniciar os contentores, aceda ao seguinte URL no seu navegador:

http://localhost:8080/swagger-ui/index.html

Autenticação
Para aceder aos endpoints protegidos, siga estes passos:

Registar um Utilizador:

Na interface do Swagger, encontre o endpoint POST /api/auth/register.

Utilize-o para criar uma nova conta, fornecendo um username, email e password.

Obter um Token de Acesso:

Agora, utilize o endpoint POST /api/auth/login com o username e password que acabou de criar.

A resposta irá conter um token JWT. Copie este token.

Autorizar Requisições no Swagger:

Clique no botão "Authorize" no canto superior direito da página do Swagger.

Na janela que aparecer, cole o seu token no campo "Value", certificando-se de que está no formato Bearer <seu-token>.

Clique em "Authorize" para fechar a janela.