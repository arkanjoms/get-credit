#!/usr/bin/env bash
echo "Iniciando build do projeto get-credit-analysis-api"
mvn -f ./get-credit-analysis-api/pom.xml clean install
echo "Iniciando build do projeto get-credit-api"
cd get-credit-api && mvn clean install && cd ..
echo "Iniciando build do projeto get-credit-ui"
cd get-credit-ui && yarn && yarn build && cd ..
echo "Construindo imagens docker"
docker build -t arkanjoms/get-credit-analysis-api ./get-credit-analysis-api
docker build -t arkanjoms/get-credit-api ./get-credit-api
docker build -t arkanjoms/get-credit-ui ./get-credit-ui
touch .build
