# ⛪ Projeto Diocese de Franca

Este repositório contém um **sistema web completo** desenvolvido para a Diocese de Franca, composto por **frontend (site)** e uma **API backend**. O projeto tem como objetivo centralizar informações da diocese, missas, contato com fiéis e gerenciamento básico de usuários.

---

## 👀 Visão Geral

O projeto é dividido em duas partes principais:

* **Frontend (Web)**: páginas em HTML, CSS e JavaScript voltadas ao público e aos fiéis.
* **Backend (API)**: aplicação Java com Spring Boot responsável por regras de negócio e integração com banco de dados.

---

## ✨ Funcionalidades

### 🌐 Frontend

* Página principal da Diocese
* Listagem de missas
* Página de contato
* Cadastro e login de fiéis
* Área do fiel (dashboard)
* Consulta de chamados
* Navegação simples e intuitiva

### ⚙️ Backend (API)

* API REST desenvolvida em Spring Boot
* Estrutura pronta para integração com banco de dados
* Organização em camadas (controller, service, repository)
* Gerenciamento de dados de usuários/fiéis

---

## 🛠️ Tecnologias Utilizadas

### 🌐 Frontend

* HTML5
* CSS3
* JavaScript

### Backend

* Java
* Spring Boot 3
* Maven
* JPA / Hibernate

### 🗄️ Banco de Dados

* Script SQL disponível no projeto (`banco.sql`)

---

## 📂 Estrutura do Projeto

```
Projeto-Diocese-de-Franca
├── Frontend
│   ├── main.html
│   ├── login_fiel.html
│   ├── cadastro_fiel.html
│   ├── missas.html
│   ├── contato.html
│   ├── fiel_dashboard.html
│   ├── consulta_chamados.html
│   └── imagens e logos
│
├── Backend (API)
│   └── diocese
│       ├── src/main/java
│       ├── src/main/resources
│       ├── pom.xml
│       └── mvnw
│
└── banco.sql
```

---

## ▶️ Como Executar o Projeto

### 🌐 Frontend

1. Extraia o projeto
2. Abra o arquivo `main.html` em um navegador

> O frontend pode ser executado diretamente no navegador, sem servidor.

---

### ⚙️ Backend (API)

#### Pré-requisitos

* Java 17 ou superior
* Maven

#### Passos

```bash
cd Projeto-Diocese-de-Franca-API/diocese
./mvnw spring-boot:run
```

Ou, no Windows:

```bash
mvnw.cmd spring-boot:run
```

A API será iniciada em:

```
http://localhost:8080
```

---

## Banco de Dados

* O arquivo `banco.sql` contém a estrutura inicial do banco
* Pode ser utilizado em MySQL ou adaptado para outro SGBD

---

## 🎯 Objetivo do Projeto

Este projeto tem caráter **acadêmico e educacional**, sendo ideal para:

* Portfólio de desenvolvimento
* Estudo de aplicações web completas (Frontend + Backend)
* Prática com Spring Boot e integração com banco de dados

---

## 👤 Autor

Desenvolvido por **sevla7**

GitHub: [https://github.com/sevla7](https://github.com/sevla7)

---

## 📄 Licença

Este projeto é de uso livre para fins educacionais.
