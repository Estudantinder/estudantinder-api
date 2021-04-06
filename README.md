![ESTUDANTINDER](https://raw.githubusercontent.com/Estudantinder/estudantinder-mobile/main/.github/README.png)

# Estudantinder API

[![Quarkus](https://img.shields.io/badge/Code-Quarkus-informational?style=flat&logo=quarkus&logoColor=white&color=4630eb)](http://estudantinder-api.herokuapp.com)
[![Maintainability](https://api.codeclimate.com/v1/badges/503d9149b187b902f383/maintainability)](https://codeclimate.com/github/Estudantinder/estudantinder-api/maintainability)

O Estudantinder lida com a procura e encontro de outros alunos, de forma a desenvolver cada vez mais sua autonomia nos estudos. Veja o [site oficial do Estudantinder](https://estudantinder.vercel.app) para mais informações

# Rotas Principais 

## POST /users
Cria um Usuário

### Request
``` application/json
{
"bio": "Biografia Teste",
"birth_date": "2002-12-08",
"classroom": "F",
"contacts": {
"facebook": "teste.teste.teste",
"instagram": "teste",
"twitter": "teste",
"whatsapp": 11900000000
},
"course_id": 5,
"email": "teste@email.com",
"gender": "Helicoptero de Ataque Boeing AH-64 Apache",
"name": "Nome Teste",
"password": "Testando1",
"school_year": 3,
"shift": 1,
"subjects_ids": [
10, 11, 12
]
}
```

### Response
``` application/json
{
  "name": "Nome Teste",
  "email": "teste@email.com",
  "password": "Testando1",
  "school_year": 3,
  "birth_date": [
    2002,
    12,
    8
  ],
  "bio": "Biografia Teste",
  "gender": "Helicoptero de Ataque Boeing AH-64 Apache",
  "shift": 1,
  "classroom": "F",
  "subjects_ids": [
    10,
    11,
    12
  ],
  "course_id": 5,
  "contacts": {
    "whatsapp": 11900000000,
    "twitter": "teste",
    "facebook": "teste.teste.teste",
    "instagram": "teste"
  }
}
```

## POST /users/login
Cria um Token JWT

### Request
``` application/json
{
"email": "teste@email.com",
"password": "Testando1"
}
```

### Response
``` application/json
{
  "jwt": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRiaWQiOjM1LCJleHAiOjE2MTgzNTIwNDIsImlhdCI6MTYxNzA1NjA0MiwianRpIjoiMTY5MzgwNTItMzliZS00NWFjLTk2YmMtM2ZkY2M5MjVjYTNhIn0.-zaufzfs8K0gNgQJmTHQ",
  "expireDate": 1618352042.174948000
}
```

## GET /students
Mostra todos os usuários que se encaixam em suas preferências

### AUTH
```
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2dpdGh1Yi5jb20vQWRhbUF1Z3VzdGluc2t5IiwidXBuIjoiZXN0dWRhbnRpbmRlckBxdWFya3VzLmlvIiwiZ3JvdXBzIjpbIlVzZXIiXSwiaWQiOjM1LCJleHAiOjE2MTgzNTIwNDIsImlhdCI6MTYxNzA1NjA0MiwianRpIjoiMTY5MzgwNTItMzliZS00NWFjLTk2YmMtM2ZkY2M5MjVjYTNhIn0.NM2viz9vD6hfrC5n9QtMg
```

### Response
``` application/json
[
    {
        id: string,
        name: string,
        bio: string,
        birth_date: Date,
        gender: string,
        shift: integer,
        school_year: integer,
        classroom: character,
        photos: string[],
        subjects: [
            {
                id: string,
                name: string,
            }
        ],
        course: {
            id: string,
            name: string,
        },
        school: {
            id: string,
            name: string,
            address: string,
            courses: Course[],
        },
    }
]
```

## POST /students/likes/{user_id}
Da like no usuário cujo id foi passado na rota

### AUTH
```
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2dpdGh1Yi5jb20vQWRhbUF1Z3VzdGluc2t5IiwidXBuIjoiZXN0dWRhbnRpbmRlckBxdWFya3VzLmlvIiwiZ3JvdXBzIjpbIlVzZXIiXSwiaWQiOjM1LCJleHAiOjE2MTgzNTIwNDIsImlhdCI6MTYxNzA1NjA0MiwianRpIjoiMTY5MzgwNTItMzliZS00NWFjLTk2YmMtM2ZkY2M5MjVjYTNhIn0.NM2viz9vD6hfrC5n9QtMg
```

### Response
```
201 - Created
```

## GET /students/matchs
Mostra os matches dó usuário cujo JWT foi passado

### AUTH
```
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2dpdGh1Yi5jb20vQWRhbUF1Z3VzdGluc2t5IiwidXBuIjoiZXN0dWRhbnRpbmRlckBxdWFya3VzLmlvIiwiZ3JvdXBzIjpbIlVzZXIiXSwiaWQiOjM1LCJleHAiOjE2MTgzNTIwNDIsImlhdCI6MTYxNzA1NjA0MiwianRpIjoiMTY5MzgwNTItMzliZS00NWFjLTk2YmMtM2ZkY2M5MjVjYTNhIn0.NM2viz9vD6hfrC5n9QtMg
```

### Response
``` application/json
[
    {
        id: string,
        name: string,
        bio: string,
        birth_date: Date,
        gender: string,
        shift: integer,
        school_year: integer,
        classroom: character,
        photos: string[],
        subjects: [
            {
                id: string,
                name: string,
            }
        ],
        course: {
            id: string,
            name: string,
        },
        school: {
            id: string,
            name: string,
            address: string,
            courses: Course[],
        },
        contacts: {
            whatsapp: integer,
            instagram: string,
            twitter: string,
            facebook: string
        }
    }
]
```

### Para ver todas as rotas, acesse a rota ``` /q/swagger-ui ``` </br> ou utilize arquivo [openapi](https://swagger.io/specification/), acessando a rota  ``` /q/openapi ```

# Formas de usar

### Heroku

A forma mais fácil de usar o aplicativo é utilizando o deploy feito no heroku [Deploy no Heroku](https://estudantinder-api.herokuapp.com/)

### Uso Local

Para utilizar o aplicativo localmente, tanto em modo de desenvolvimento, quanto realizando o build do projeto siga para [Instalação](#-instalação)

# Instalação

Você vai precisar de algumas ferramentas para rodar o projeto localmente. Caso queira só utilizar a api, veja o tópico [Formas de Usar](#formas-de-usar)

## Dependências

### GIT

- Instale o git em sua máquina: [https://git-scm.com/downloads](https://git-scm.com/downloads)
- Com o git instalado na sua máquina, clone o repositório usando o botão **Code** em https://github.com/Estudantinder/estudantinder-api
- Caso seja necessario, utilize um tutorial basico, como esse https://www.hostinger.com.br/tutoriais/tutorial-do-git-basics-introducao

### JDK 11

- Baixe e instale a JDK 11 de sua preferencia, por exemplo a da Oracle <https://www.oracle.com/java/technologies/javase-jdk11-downloads.html>
- Para testar se a JDK foi instalada com sucesso, rode o seguinte comando `java --version` no terminal. Deverá aparecer a versão da JDK instalada

### Maven

- Baixe a versão mais nova do maven em <https://maven.apache.org/download.cgi>
- Caso seja necessario utilize esse tutorial <https://dicasdejava.com.br/como-instalar-o-maven-no-windows/>
- Para testar se o Maven está instalado na sua máquina, execute o comando `mvn -v`. Deverá aparecer a versão do Maven instalada

### PostgreSQL

- Para o postgres é recomendado a utilização em docker, primeiro instale o [Docker Desktop](https://www.docker.com/products/docker-desktop), depois siga esse simples tutorial <http://www.mariolb.com.br/blog/2020/09/19/postgresql_no_docker.html>
- Após a instalação, crie o banco de dados do Estudantinder com o comando
```SQL
    CREATE DATABASE estudantinder
```

---

**Pronto!** Você já pode começar a usar a API, vá para a aba [Scripts](#scripts) para ver quais scripts estão disponíveis para uso

## Comandos Disponíveis

Todos esses scripts pode ser rodados na sua máquina usando o Maven

Os parâmetros dos scripts serão representados com um prefixo `$`

### quarkus:dev

Para rodar a api em modo de desenvolvimento, utilize o comando

```shell script
./mvnw quarkus:dev
```
ou
```shell script
mvn quarkus:dev
```

### quarkus:build

Para realizar o build do app, utilize o comando

```shell script
./mvnw quarkus:build
```
ou
```shell script
mvn quarkus:build
```

No final você terá um .jar executável na pasta /target

### test

Para executar os testes do código, utilize o comando

```shell script
./mvnw test
```
ou
```shell script
mvn test
```

### Para saber mais sobre os comandos disponiveis, acesse <https://quarkus.io/guides/maven-tooling>

## UI (Front End)

Você pode ver o backend da aplicação no seguinte repositório: https://github.com/Estudantinder/estudantinder-mobile
