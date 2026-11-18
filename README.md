
# OmniAI Endpoint Manager

Plataforma inteligente de gerenciamento, monitoramento e análise de endpoints Windows, desenvolvida com Java, Spring Boot e Inteligência Artificial.

## Sobre o projeto

O OmniAI Endpoint Manager tem como objetivo centralizar o gerenciamento de dispositivos, permitindo acompanhar informações de hardware, monitorar indicadores operacionais, identificar anomalias e auxiliar administradores por meio de inteligência artificial.

A plataforma será dividida em três componentes principais:

- **OmniAI Backend:** API REST desenvolvida com Java e Spring Boot.
- **OmniAI Agent:** agente Java responsável pela coleta autorizada de informações dos dispositivos Windows.
- **OmniAI Frontend:** painel administrativo desenvolvido com React e TypeScript.

## Tecnologias

| Tecnologia | Aplicação |
|---|---|
| Java 21 | Linguagem principal |
| Spring Boot | Backend |
| Spring Security | Autenticação e autorização |
| PostgreSQL | Banco de dados |
| React + TypeScript | Interface |
| Docker | Infraestrutura |
| Spring AI | Integração com IA |
| Git | Controle de versão |

## Arquitetura

```text
omniai-endpoint-manager/
├── omniai-backend/
├── omniai-agent/
├── omniai-frontend/
├── infrastructure/
│   ├── docker/
│   └── database/
├── docs/
├── README.md
└── .gitignore
```

## Funcionalidades planejadas

- [ ] Autenticação e gerenciamento de usuários.
- [ ] Cadastro e inventário de endpoints.
- [ ] Comunicação segura com agentes Windows.
- [ ] Monitoramento de hardware e sistema operacional.
- [ ] Central de alertas.
- [ ] Histórico de eventos e auditoria.
- [ ] Dashboard administrativo.
- [ ] Assistente inteligente com IA.
- [ ] Análise de anomalias e recomendações.
- [ ] Relatórios inteligentes.

## Desenvolvimento

O projeto está em desenvolvimento e será construído por etapas (EPICs), priorizando arquitetura organizada, segurança, testes automatizados e documentação.

## Segurança

O sistema deverá utilizar autenticação, autorização baseada em permissões e comunicação segura entre seus componentes. Credenciais e chaves privadas não devem ser armazenadas no repositório.

---

**OmniAI Endpoint Manager**  
Intelligent Endpoint Management Platform.
