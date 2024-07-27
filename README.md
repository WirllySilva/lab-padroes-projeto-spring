# Library System

Este é um projeto de sistema de biblioteca desenvolvido em Java utilizando Spring Framework, Maven, JPA, banco de dados H2, e API Rest. O sistema armazena informações de clientes, incluindo endereços, e utiliza uma API de CEP para facilitar a busca de endereços.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven
- API Rest
- RestTemplate para integração com API de CEP

## Funcionalidades

- CRUD de Clientes
- Armazenamento de Endereços
- Integração com API de CEP para busca de endereços


## Compile o projeto:
mvn clean install

## Execute o projeto:
mvn spring-boot:run

## Configuração do Projeto

### Pré-requisitos

- JDK 11+
- Maven

### Passos para Configuração

1. Clone o repositório:

```sh
git clone https://github.com/seu-usuario/library-system.git
cd library-system

Endpoints da API
Clientes
GET /api/customers: Retorna todos os clientes.
GET /api/customers/{id}: Retorna um cliente pelo ID.
POST /api/customers: Cria um novo cliente.
PUT /api/customers/{id}: Atualiza um cliente existente.
DELETE /api/customers/{id}: Deleta um cliente pelo ID.
Integração com API de CEP
A integração com a API de CEP utiliza o RestTemplate do Spring Boot. A API utilizada é a ViaCEP.

Contribuição
Se você quiser contribuir para o projeto, sinta-se à vontade para abrir um pull request ou criar uma issue.

Licença
Este projeto está licenciado sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.

Este README fornece uma visão geral do projeto, suas funcionalidades, estrutura e instruções de configuração. Você pode ajustá-lo conforme necessário para se adequar melhor ao seu projeto específico.
