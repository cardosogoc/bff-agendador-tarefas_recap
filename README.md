# 🚀 BFF Agendador

Backend for Frontend (BFF) desenvolvido em **Java com Spring Boot**, responsável por centralizar e intermediar a comunicação entre o cliente e os microsserviços do sistema de agendamento.

O projeto utiliza **Feign Client** para comunicação entre serviços, organização baseada em camadas, DTOs para transferência de dados, tratamento personalizado de exceções e configuração de segurança.

## 🛠️ Tecnologias

* ☕ Java
* 🌱 Spring Boot
* 🔗 OpenFeign
* 🔐 Spring Security
* 📦 Gradle
* 🧩 Lombok
* 📄 DTOs
* 🐳 Docker
* 📚 Swagger / OpenAPI
* 📊 SonarQube

## 🏗️ Arquitetura

O projeto atua como uma camada intermediária entre o cliente e os microsserviços da aplicação.

```text
                    ┌─────────────────┐
                    │     Cliente     │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │  BFF Agendador  │
                    │   Spring Boot   │
                    └────────┬────────┘
                             │
              ┌──────────────┼──────────────┐
              │              │              │
              ▼              ▼              ▼
        ┌──────────┐   ┌────────────┐   ┌────────────┐
        │ Usuário  │   │  Tarefas   │   │ Notificação│
        │   API    │   │    API     │   │    API     │
        └──────────┘   └────────────┘   └────────────┘
```

O BFF concentra as chamadas necessárias para o frontend, evitando que o cliente precise conhecer diretamente os diferentes microsserviços da aplicação.

## 📂 Estrutura do projeto

```text
src
└── main
    └── java
        └── com.javanauta.bffagendador
            │
            ├── business
            │   ├── dto
            │   │   ├── in
            │   │   └── out
            │   │
            │   ├── enums
            │   └── service
            │
            ├── controller
            │
            ├── infrastructure
            │   ├── client
            │   │   ├── config
            │   │   │   ├── FeignConfig
            │   │   │   └── FeignError
            │   │   │
            │   │   ├── EmailClient
            │   │   ├── TarefasClient
            │   │   └── UsuarioClient
            │   │
            │   ├── configs
            │   │   └── CorsConfig
            │   │
            │   ├── exceptions
            │   │   ├── dto
            │   │   │   └── ErrorResponseDTO
            │   │   ├── BusinessException
            │   │   ├── ConflictException
            │   │   ├── IllegalArgumentException
            │   │   ├── ResourceNotFoundException
            │   │   └── UnauthorizedException
            │   │
            │   └── security
            │       └── SecurityConfig
            │
            └── BffAgendadorApplication
```

## 🔗 Comunicação entre microsserviços

A comunicação com os demais serviços é realizada utilizando **OpenFeign**, permitindo que o BFF consuma as APIs de forma declarativa.

Os principais clientes são:

* `UsuarioClient` — comunicação com o microsserviço de usuários.
* `TarefasClient` — comunicação com o microsserviço de tarefas.
* `EmailClient` — comunicação com o serviço responsável pelas notificações.

Também existem configurações específicas para o Feign, incluindo tratamento de erros através de `FeignError`.

## 🔐 Segurança

O projeto possui uma camada de segurança utilizando **Spring Security**.

A configuração de segurança está centralizada em:

```text
infrastructure/security/SecurityConfig
```

O BFF também trabalha com autenticação baseada em **JWT**, realizando o controle de acesso às operações protegidas.

## 📦 DTOs

Os dados utilizados na comunicação da aplicação são separados em DTOs de entrada e saída:

```text
business
└── dto
    ├── in
    └── out
```

Essa separação evita a exposição direta das estruturas internas da aplicação e facilita o controle dos dados recebidos e enviados pela API.

## ⚠️ Tratamento de exceções

O projeto possui exceções personalizadas para representar diferentes situações da aplicação:

* `BusinessException`
* `ConflictException`
* `ResourceNotFoundException`
* `UnauthorizedException`
* `IllegalArgumentException`

Também existe um DTO específico para padronização das respostas de erro:

```text
ErrorResponseDTO
```

Isso permite que a API mantenha respostas de erro mais consistentes para o cliente.

## 🌐 CORS

A configuração de **CORS (Cross-Origin Resource Sharing)** está centralizada em:

```text
infrastructure/configs/CorsConfig
```

Essa configuração permite controlar quais origens podem realizar requisições para o BFF.

## 📖 Documentação da API

A API pode ser documentada e testada através do **Swagger / OpenAPI**.

Após iniciar a aplicação, acesse a interface do Swagger através da URL configurada para o projeto.

## 🐳 Docker

O projeto também pode ser executado utilizando Docker, permitindo que o BFF seja executado junto aos demais microsserviços da aplicação.

Exemplo de arquitetura utilizando containers:

```text
┌──────────────────────────────────────────┐
│              Docker Compose              │
│                                          │
│  ┌────────────┐     ┌───────────────┐   │
│  │    BFF     │────▶│    Usuário    │   │
│  └────────────┘     └───────────────┘   │
│        │                                 │
│        ├──────────────▶ Tarefas          │
│        │                                 │
│        └──────────────▶ Notificação      │
│                                          │
└──────────────────────────────────────────┘
```

## ▶️ Como executar

### Pré-requisitos

* Java instalado
* Gradle
* Docker e Docker Compose (opcional)

### Executando com Gradle

Clone o projeto e execute:

```bash
./gradlew bootRun
```

No Windows:

```bash
gradlew.bat bootRun
```

### Executando com Docker

Caso esteja utilizando Docker Compose:

```bash
docker compose up --build
```

## ⚙️ Configuração

As URLs dos microsserviços devem ser configuradas de acordo com o ambiente em que a aplicação está sendo executada.

Em um ambiente Docker Compose, os serviços podem ser acessados através dos nomes dos respectivos containers.

Exemplo:

```text
USUARIO_URL=http://usuario:8080/usuario
AGENDADOR_TAREFAS_URL=http://agendador-tarefas:8081/tarefas
NOTIFICACAO_URL=http://notificacao:8082/email
```

> Recomenda-se utilizar variáveis de ambiente para informações específicas de cada ambiente, evitando deixar configurações sensíveis diretamente no código.

## 📊 Qualidade de código

O projeto utiliza **SonarQube** para análise estática e acompanhamento da qualidade do código.

A análise permite identificar problemas relacionados a:

* Bugs
* Vulnerabilidades
* Code Smells
* Cobertura de testes
* Duplicação de código
* Manutenibilidade

## 🎯 Objetivo

O projeto foi desenvolvido com o objetivo de aplicar conceitos de **arquitetura de microsserviços**, comunicação entre APIs e desenvolvimento de aplicações backend utilizando o ecossistema Spring.

Entre os principais conceitos aplicados estão:

* Arquitetura BFF
* Microsserviços
* REST APIs
* OpenFeign
* DTOs
* Spring Security
* JWT
* Tratamento de exceções
* CORS
* Docker
* Swagger / OpenAPI
* SonarQube
* Separação de responsabilidades
* Configuração por variáveis de ambiente

## 🔗 Links do Projeto

### 📂 Microsserviços

* **Usuário:** [usuario_recap — GitHub](https://github.com/cardosogoc/usuario_recap?utm_source=chatgpt.com)
* **Agendador de Tarefas:** [agendador-tarefas_recap — GitHub](https://github.com/cardosogoc/agendador-tarefas_recap?utm_source=chatgpt.com)
* **Notificação:** [notificacao_recap — GitHub](https://github.com/cardosogoc/notificacao_recap?utm_source=chatgpt.com)

### 🐳 Docker Hub

* **BFF Agendador:** [Docker Hub — cardosogoc/bff-agendador](https://hub.docker.com/r/cardosogoc/bff-agendador/tags?utm_source=chatgpt.com)

## 👨‍💻 Autor

**Gabriel Cardoso**

Projeto desenvolvido para estudos e evolução prática em **desenvolvimento Backend Java e Spring Boot**.
