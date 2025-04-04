# SGHSS - Sistema de Gestão Hospitalar e de Serviços de Saúde

## 📌 Descrição do Projeto
O **SGHSS (Sistema de Gestão Hospitalar e de Serviços de Saúde)** é uma solução completa para a administração de unidades hospitalares, oferecendo funcionalidades como gestão de pacientes, profissionais de saúde, agendamento de consultas, telemedicina, controle de leitos e auditoria. O sistema segue as melhores práticas de desenvolvimento, garantindo segurança, escalabilidade e conformidade com a LGPD.

---

## 🚀 Tecnologias Utilizadas
### 🔧 Backend:
- **Java 17**
- **Spring Boot 3+**
- **Spring Security (JWT & OAuth2)**
- **Spring Data JPA**
- **MySQL**
- **Lombok**
- **Swagger/OpenAPI**
- **Jitsi Meet (para Telemedicina)**
- **Docker** (opcional)

### 🎨 Frontend:
- **Thymeleaf**
- **Bootstrap**
- **JavaScript**
- **HTML5 & CSS3**

---

## 🎯 Funcionalidades Principais
### 🏥 Gestão Hospitalar
✔️ Cadastro e gerenciamento de pacientes e médicos  
✔️ Controle de leitos hospitalares  
✔️ Histórico clínico dos pacientes  
✔️ Prescrições digitais  
✔️ Agendamento e gerenciamento de consultas  
✔️ Telemedicina integrada com **Jitsi Meet**  
✔️ Auditoria e logs detalhados para conformidade com a LGPD  

### 🔑 Segurança
✔️ Autenticação e autorização com **Spring Security** (JWT & OAuth2)  
✔️ Perfis de usuários:
   - **SUPER ADMIN** (acesso total, gerenciamento de administradores)
   - **ADMIN** (gestão hospitalar, médicos e pacientes)
   - **MÉDICO** (acesso aos pacientes e consultas)
   - **PACIENTE** (autocadastro e agendamento de consultas)  
✔️ Logs de auditoria e controle de permissões  

### 📊 Relatórios
✔️ Relatórios administrativos com dados hospitalares  
✔️ Exportação de auditoria em **PDF** e **CSV**  

---

## 📂 Estrutura do Projeto
```
SGHSS
│── src/main/java/com/vidaplus/sghss
│   ├── controller/         # Controladores REST e Thymeleaf
│   ├── entity/             # Modelos das entidades do banco
│   ├── repository/         # Repositórios JPA
│   ├── service/            # Regras de negócio
│   ├── security/           # Configuração do Spring Security
│   ├── config/             # Configurações gerais do projeto
│── src/main/resources/
│   ├── templates/          # Páginas HTML (Thymeleaf)
│   ├── static/             # Arquivos CSS e JS
│── pom.xml                 # Dependências do Maven
│── README.md               # Documentação
```

---

## 🛠️ Configuração do Ambiente
### 1️⃣ Pré-requisitos
- **JDK 17**
- **MySQL** (ou Docker para rodar o banco de dados)
- **Maven**

### 2️⃣ Configurar o Banco de Dados
Crie um banco de dados no MySQL:
```sql
CREATE DATABASE sghss;
```
Configure o `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sghss
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
```

### 3️⃣ Executar o Projeto
```sh
mvn spring-boot:run
```
O sistema estará disponível em: `http://localhost:8080`

---

## 🧑‍💻 Rotas Principais
### 🔐 Autenticação
- `POST /auth/register` → Criar conta (Paciente/Admin)
- `POST /auth/login` → Login (JWT)

### 👤 Gestão de Usuários
- `GET /admin/pacientes` → Listar pacientes
- `POST /admin/medicos` → Criar médico

### 🏥 Gestão Hospitalar
- `POST /consulta/agendar` → Agendar consulta
- `GET /consulta/{id}` → Consultar detalhes da consulta
- `GET /leitos/disponiveis` → Listar leitos disponíveis

### 📊 Relatórios
- `GET /admin/relatorios/pdf` → Baixar relatório em PDF
- `GET /admin/relatorios/csv` → Baixar relatório em CSV

---

## 🔒 Segurança e Conformidade
- **Criptografia de Senhas** com **BCrypt**
- **Tokens JWT** para autenticação segura
- **Controle de Acesso** baseado em roles
- **Auditoria de Dados** (logs de ações administrativas)
- **Conformidade com a LGPD** (armazenamento seguro de dados sensíveis)

---

## 📌 Melhorias Futuras
✅ Integração com **AWS S3** para armazenamento de arquivos médicos  
✅ Notificações via **E-mail e SMS** para pacientes e médicos  
✅ Dashboard interativo com estatísticas hospitalares  

---

## 👨‍💻 Contribuição
Sinta-se à vontade para contribuir com melhorias! Clone o projeto e abra um Pull Request. 
```sh
git clone https://github.com/seu-usuario/sghss.git
```

---

## 📄 Licença
Projeto desenvolvido para fins acadêmicos. Todos os direitos reservados. 📜
