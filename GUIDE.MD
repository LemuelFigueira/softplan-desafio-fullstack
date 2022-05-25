## RODANDO API ##
- com o jdk11 instalado em sua máquina e o banco rodando, prossiga.
- entrar na pasta api
- executar o comando `docker-compose up --force-recreate -V`
- ou executar os comandos:
- `mvn clean`
- `mvn spring-boot:run`


## RODANDO WEB ##
- com o node 14.17.0 instalado em sua máquina, prossiga.
- entrar na pasta app
- executar o comando `yarn` e o comando `yarn dev`

## RODANDO BANCO LOCAL ##
- na pasta raiz do projeto executar os seguintes comandos:
- `docker-compose -f docker-compose-mysql.yaml up --force-recreate -V`