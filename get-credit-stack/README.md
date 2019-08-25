# get-credit-stack

Projeto `docker-compose` para inicialização dos recursos necessário para utilização da aplicação `get-credit`.

Os serviços abaixo estão configurado:

- postgres - banco de dados utilizado pela aplicação.
- get-credit-ui - módulo frontend.
- get-credit-api - módulo backend.
- get-credit-analysis-api - motor de análise de crédito.
- get-credit-reverse-proxy - proxy reverso com `nginx` para roteamento dos serviços.
