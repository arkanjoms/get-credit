# get-credit-api

Módulo backend da aplicação get-credit.

### Tecnologias

Para desenvolvimento deste módulo foram utilizadas as seguintes tecnologias:

- spring-boot
- spring-boot-data-jpa
- swagger
- lombok
- openfeign
- postgres
- docker

## Pré requisitos

- JDK 8
- Postgres

> Configurar o arquivo `src/main/resources/application.properties` com as configurações do serviço do postgres e também a url do serviço de análise de cŕedito na propriedade `get-credit-analysis-api.url`.

### Configurando

Para configurar o projeto basta executar o comando abaixo:

```bash
$ mvn clean compile spring-boot:run
```