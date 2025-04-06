# 🔐 Security Policy

## 📦 Supported Versions

As versões abaixo estão sendo monitoradas para atualizações de segurança:

| Versão  | Suporte de Segurança |
|---------|----------------------|
| 1.0.x   | ✅ Suportada          |
| < 1.0   | ❌ Não suportada      |

---

## 🛡️ Medidas de Segurança Implementadas

O **SGHSS (Sistema de Gestão Hospitalar e de Serviços de Saúde)** foi projetado para garantir a segurança de dados sensíveis em conformidade com a **Lei Geral de Proteção de Dados (LGPD)**.

### 🔐 Autenticação e Autorização
- Implementação com **Spring Security**.
- Autenticação via **JWT (JSON Web Token)**.
- Roles: `SUPER_ADMIN`, `ADMIN`, `MEDICO`, `PACIENTE`.
- Permissões diferentes para cada tipo de usuário com base em seus papéis.

### 🔒 Criptografia
- Senhas são armazenadas com **BCrypt** para máxima segurança.

### 🛡️ Proteção Contra Ameaças
- Desabilitação de CSRF em endpoints REST.
- Configuração de **Security Headers** para mitigar ataques XSS e Clickjacking.
- Filtro de requisição JWT que valida tokens antes da autorização.

### 📜 Auditoria e Logs
- Ações críticas e de login são logadas.
- Sistema preparado para manter **registros de auditoria** em conformidade com a LGPD.

### 🧼 Validação de Dados
- Uso de **DTOs com Bean Validation (Jakarta)**.
- Sanitização de campos para evitar **injeção de SQL** e **cross-site scripting (XSS)**.

---

## 📣 Reporting a Vulnerability

Se você encontrou uma vulnerabilidade neste projeto, abra uma issue pública. 

Você pode esperar uma resposta em até **5 dias úteis**. Após validação, correções e atualizações serão disponibilizadas com prioridade.

---

## 🔗 Recursos

- [LGPD - Lei Geral de Proteção de Dados (Lei nº 13.709/2018)](https://www.gov.br/governodigital/pt-br/lgpd)
- [Documentação do Spring Security](https://spring.io/projects/spring-security)

