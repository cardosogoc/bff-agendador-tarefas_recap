# 🚀 BFF Agendador

Backend for Frontend (BFF) do sistema **Agendador de Tarefas**, desenvolvido em **Java com Spring Boot**. Ele centraliza e intermedia a comunicação entre o front-end e os microsserviços da aplicação (usuário, tarefas e notificação), de forma que o cliente conheça apenas uma API.

> O front-end deste sistema está em: [agendador-tarefas-front](https://github.com/cardosogoc/agendador-tarefas-front)

---

## ✨ Destaques

- Comunicação declarativa com os microsserviços via **OpenFeign**
- Autenticação e controle de acesso com **Spring Security** e **JWT**
- DTOs de entrada e saída separados
- Exceções personalizadas com resposta de erro padronizada
- Configuração de **CORS**
- Documentação interativa com **Swagger / OpenAPI**
- Imagem **Docker** publicada no Docker Hub
- Análise de qualidade de código com **SonarQube**

---

## 🛠️ Tecnologias

- ☕ Java
- 🌱 Spring Boot
- 🔗 OpenFeign
- 🔐 Spring Security (JWT)
- 📦 Gradle
- 🧩 Lombok
- 🐳 Docker
- 📚 Swagger / OpenAPI
- 📊 SonarQube

---

## 🏗️ Arquitetura

```text
                    ┌─────────────────┐
                    │    Front-end    │
                    │     Angular     │
                    └────────┬────────┘
                             │
                             ▼
                    ┌─────────────────┐
                    │  BFF Agendador  │
                    │   Spring Boot   │
                    └────────┬────────┘
                             │
              ┌──────────────┼──────────────┐
              ▼              ▼              ▼
        ┌──────────┐   ┌──────────┐   ┌────────────┐
        │ Usuário  │   │ Tarefas  │   │ Notificação│
        │   API    │   │   API    │   │    API     │
        └──────────┘   └──────────┘   └────────────┘
```

### Clientes Feign

| Cliente | Responsabilidade |
| --- | --- |
| `UsuarioClient` | Comunicação com o microsserviço de usuários |
| `TarefasClient` | Comunicação com o microsserviço de tarefas |
| `EmailClient` | Comunicação com o serviço de notificações |

Os erros das chamadas Feign são tratados em `FeignError`.

---

## 📂 Estrutura do projeto

```text
src/main/java/com/javanauta/bffagendador
├── business
│   ├── dto
│   │   ├── in                 # Dados de entrada
│   │   └── out                # Dados de saída
│   ├── enums
│   └── service
├── controller
├── infrastructure
│   ├── client
│   │   ├── config             # FeignConfig e FeignError
│   │   ├── EmailClient
│   │   ├── TarefasClient
│   │   └── UsuarioClient
│   ├── configs
│   │   └── CorsConfig
│   ├── exceptions             # Exceções personalizadas e ErrorResponseDTO
│   └── security
│       └── SecurityConfig
└── BffAgendadorApplication
```

---

## 🔐 Segurança

A configuração de segurança fica em `infrastructure/security/SecurityConfig`. O acesso às operações protegidas é controlado por **token JWT**, enviado no header `Authorization`.

## ⚠️ Tratamento de exceções

Exceções personalizadas representam as diferentes situações da aplicação:

- `BusinessException`
- `ConflictException`
- `ResourceNotFoundException`
- `UnauthorizedException`
- `IllegalArgumentException`

As respostas de erro seguem o padrão do `ErrorResponseDTO`, mantendo a API consistente para o cliente.

## 🌐 CORS

A configuração está em `infrastructure/configs/CorsConfig` e define quais origens podem fazer requisições ao BFF.

---

## ✅ Pré-requisitos

- JDK instalado
- Docker e Docker Compose (opcional)

O projeto já inclui o Gradle Wrapper (`gradlew`), então não é necessário instalar o Gradle.

## ⚙️ Configuração

As URLs dos microsserviços são configuradas por **variáveis de ambiente**, de acordo com o ambiente de execução. Em um ambiente com Docker Compose, use o nome dos containers:

```text
USUARIO_URL=http://usuario:8080/usuario
AGENDADOR_TAREFAS_URL=http://agendador-tarefas:8081/tarefas
NOTIFICACAO_URL=http://notificacao:8082/email
```

> Evite deixar informações sensíveis diretamente no código; prefira variáveis de ambiente.

## ▶️ Como executar

### Com Gradle

```bash
./gradlew bootRun
```

No Windows:

```bash
gradlew.bat bootRun
```

### Com Docker

Usando a imagem publicada no Docker Hub: [cardosogoc/bff-agendador](https://hub.docker.com/r/cardosogoc/bff-agendador)

Ou, com Docker Compose, junto dos demais serviços:

```bash
docker compose up --build
```

---

## 📖 Documentação da API

Com a aplicação rodando, acesse o Swagger:

**http://localhost:8083/swagger-ui/index.html**

---

## 📊 Qualidade de código

O **SonarQube** é usado para análise estática, acompanhando bugs, vulnerabilidades, code smells, cobertura de testes, duplicação de código e manutenibilidade.

---

## 🎯 Objetivo

Projeto desenvolvido para aplicar, na prática, conceitos de **arquitetura de microsserviços**, comunicação entre APIs e desenvolvimento backend com o ecossistema Spring: arquitetura BFF, REST, OpenFeign, DTOs, Spring Security com JWT, tratamento de exceções, CORS, Docker, Swagger e SonarQube.

---

## 🔗 Links do projeto

**Microsserviços**

- [usuario_recap](https://github.com/cardosogoc/usuario_recap)
- [agendador-tarefas_recap](https://github.com/cardosogoc/agendador-tarefas_recap)
- [notificacao_recap](https://github.com/cardosogoc/notificacao_recap)

**Front-end**

- [agendador-tarefas-front](https://github.com/cardosogoc/agendador-tarefas-front)

**Docker Hub**

- [cardosogoc/bff-agendador](https://hub.docker.com/r/cardosogoc/bff-agendador)

---

## 👨‍💻 Autor

**Gabriel de Oliveira Cardoso**

- GitHub: [@cardosogoc](https://github.com/cardosogoc)
- LinkedIn: [gabriel-cardoso-developer](https://www.linkedin.com/in/gabriel-cardoso-developer/)
