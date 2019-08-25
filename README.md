# get-credit

Aplicação para análise de crédito.

### Módulos

Este projeto possui os seguintes módulos:

- [get-credit-ui](get-credit-ui/) - módulo frontend.
- [get-credit-api](get-credit-api) - módulo backend.
- [get-credit-analysis-api](get-credit-analysis-api) - módulo backend com motor de análise de crédito.
- [get-credit-stack](get-credit-stack) - configuração de ambiente com `docker-compose`.

### Pré requisitos

- JDK 8
- Maven 3.6+
- node 12+
- yarn 1.17+
- postgres 11+ (necessário somente para utilização em desenvolvimento, pois ao iniciar o ambiente é criado um container do mesmo).
- Docker 19.03

### Configurando

Para construir as imagens docker necessárias para utilização da aplicação, executar o comando abaixo:

```bash
$ ./build.sh
```

Após a construção terminar executar o script `getcredit.sh`, o mesmo possui as seguinte opções:

- **start** - inicia os serviços do ambiente.
- **stop** - para os serviços do ambiente.
- **restart** - reinicia os serviços do ambiente.
- **clean** - removo os containers, volume e rede criado pelo ambiente.

Exemplo para iniciar o ambiente:

```bash
$ ./getcredit.sh start
```

> Utilizado ambiente linux para desenvolvimento e testes.
> Sugestão para teste é utilizar o site [play-with-docker](https://labs.play-with-docker.com/) instalando as dependências necessárias antes de executar.
