<p align="center">
  <img src="https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring_Boot-2.6.4-6db33f?style=flat-square&logo=spring-boot&logoColor=white"/>
  <img src="https://img.shields.io/badge/Bootstrap-5.3-7952b3?style=flat-square&logo=bootstrap&logoColor=white"/>
  <img src="https://img.shields.io/badge/Thymeleaf-3-85ca00?style=flat-square&logo=thymeleaf&logoColor=white"/>
  <img src="https://img.shields.io/badge/License-GPL%20v3-blue?style=flat-square"/>
</p>

<h1 align="center">📚 Library Management System</h1>

<p align="center">
  <strong>Refatoração Assistida por IA com Claude Code</strong><br>
  <em>Universidade Federal de Mato Grosso — Ciência da Computação</em>
</p>

---

## 👥 Equipe

| Nome |
|------|
| **Danilo Barbosa da Silva**
| **Tiago dos Reis Jesus**
| **Gabriel Pereira Martins**
| **Andre Luiz Prado Pichara**


---

## 🎯 Sobre o Projeto

Este repositório contém a versão aprimorada do **Library Management System**, desenvolvida como requisito avaliativo para a disciplina de **Projeto de Software** do curso de Ciência da Computação da UFMT.

O objetivo foi aplicar técnicas de **Desenvolvimento Assistido por IA** (Agentic Coding) utilizando o **Claude Code**. Partimos de um sistema legado com vulnerabilidades e aplicamos refatorações arquiteturais, focando em:

- **Segurança:** Implementação do padrão DTO para mitigar falhas de *Mass Assignment* e externalização de credenciais.
- **Manutenibilidade:** Eliminação de *Magic Strings* através da criação de constantes de rotas.
- **Desempenho:** Otimização da paginação utilizando os recursos nativos do Spring Data JPA.
- **Interface:** Migração para Bootstrap 5 com CSS customizado, dashboard e tratamento global de exceções.

---


---

## ✨ Melhorias Implementadas

### Backend

| Melhoria | Descrição |
|----------|-----------|
| **DTOs com Bean Validation** | `BookDTO`, `AuthorDTO`, `CategoryDTO`, `PublisherDTO` — separação entre persistência e formulário |
| **Tratamento Global de Exceções** | `@ControllerAdvice` com `@ExceptionHandler` para erros 404 e 500 |
| **Paginação com Pageable** | Consulta via Spring Data JPA (antes carregava tudo em memória) |
| **Constantes de Rotas** | Eliminação de *Magic Strings* nos endpoints |
| **JOIN FETCH** | Query personalizada para evitar `LazyInitializationException` nos livros |

### Frontend

| Melhoria | Descrição |
|----------|-----------|
| **Bootstrap 5.3** | Upgrade completo com Bootstrap Icons |
| **Dashboard** | Cards com estatísticas + tabela de livros recentes |
| **CSS Customizado** | Tabelas profissionais com header gradiente, hover suave, numeração |
| **Validação Visual** | Mensagens de erro inline nos formulários |
| **Templates de Detalhe** | Criadas páginas que não existiam no original |

---

## 🛠️ Stack Tecnológica

| Camada | Tecnologia |
|--------|------------|
| Backend | Java 17, Spring Boot 2.6.4, Spring Security |
| Persistência | Spring Data JPA + H2 Database |
| Template | Thymeleaf + thymeleaf-extras-springsecurity5 |
| Validação | Bean Validation (Hibernate Validator) |
| Frontend | Bootstrap 5.3 + Bootstrap Icons |
| Build | Apache Maven |

---

## 🚀 Como Executar

**Pré-requisitos:** JDK 17 e Apache Maven 3.6+

```bash
# Clone o repositório
git clone https://github.com/dbsilva3/librarymanagementsystem.git
cd librarymanagementsystem/

# Compile e execute
mvn clean spring-boot:run

# Acesse no navegador
http://localhost:9080
```

**Credenciais:**
| Email | Senha |
|-------|-------|
| `admin@admin.in` | `Temp123` |

---

## 📁 Estrutura do Projeto

```
librarymanagementsystem/
├── pom.xml
├── README.md
├── LICENSE
└── src/main/
    ├── java/com/knf/dev/librarymanagementsystem/
    │   ├── Application.java
    │   ├── constant/                  # Enums (Item)
    │   ├── controller/                # Controllers MVC
    │   ├── dto/                       # DTOs com Bean Validation
    │   ├── entity/                    # Entidades JPA
    │   ├── exception/                 # GlobalExceptionHandler + NotFoundException
    │   ├── repository/                # Spring Data Repositories
    │   ├── securityconfig/            # SecurityConfiguration
    │   ├── service/                   # Interfaces + Implementações
    │   ├── util/                      # Mapper
    │   └── vo/                        # Value Objects (CSV)
    └── resources/
        ├── application.properties
        └── templates/
            ├── common/                # Header + Footer
            ├── dashboard.html
            ├── error/404.html
            ├── login.html
            ├── list-*.html            # Listagens com paginação
            ├── add-*.html             # Formulários com validação
            └── update-*.html          # Edição com validação
```

---

## 🤖 Como o Claude Code Foi Utilizado

| Fase | O que foi feito com Claude Code |
|------|--------------------------------|
| **Análise** | Explorou toda a base de código, identificando vulnerabilidades e oportunidades de melhoria |
| **Planejamento** | Criou um plano detalhado de refatoração organizado em fases priorizadas |
| **Implementação** | Gerou DTOs, reescreveu controllers, implementou paginação e tratamento de exceções |
| **Frontend** | Reescreveu todas as 19 páginas HTML migrando de Bootstrap 4 para 5 |
| **Correção de Bugs** | Identificou templates ausentes e corrige a paginação em memória |
| **Documentação** | Gerou este README com estrutura profissional |

---

## 📋 Comparativo: Original vs. Versão Atual

| Aspecto | Original | Atual |
|---------|----------|-------|
| Bootstrap | 4.1.3 | 5.3 com ícones |
| Validação | Somente client-side (JS) | Server-side com Bean Validation |
| Paginação | Em memória (carrega tudo) | Spring Data JPA Pageable |
| DTOs | Não existiam | 4 DTOs com validação |
| Dashboard | Não existia | Página com estatísticas |
| Exceções | Nenhuma | `@ControllerAdvice` global |
| CSS | Nenhum customizado | Tabelas profissionais, cards, efeitos |

---

## 📄 Licença

Este projeto está licenciado sob a [GNU General Public License v3.0](LICENSE).
