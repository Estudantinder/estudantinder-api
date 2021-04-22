![ESTUDANTINDER](https://raw.githubusercontent.com/Estudantinder/estudantinder-mobile/main/.github/README.png)

</br>

## [README EM PORTUGUÊS](README.md)

</br>

# Estudantinder API

Estudantinder deals with the search and encounter of other students, in order to increasingly develop their autonomy in their studies. See [Estudantinder's official website](https://estudantinder.vercel.app/) for more information

# Main Routes

## POST /users

Creation of an user

### Request

```json
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

```json
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

Creation of a JWT Token

### Request

```json
{
"email": "teste@email.com",
"password": "Testando1"
}
```

### Response

```json
{
  "jwt": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRiaWQiOjM1LCJleHAiOjE2MTgzNTIwNDIsImlhdCI6MTYxNzA1NjA0MiwianRpIjoiMTY5MzgwNTItMzliZS00NWFjLTk2YmMtM2ZkY2M5MjVjYTNhIn0.-zaufzfs8K0gNgQJmTHQ",
  "expireDate": 1618352042.174948000
}
```

## GET /students

Shows all users that fit your preferences

### AUTH

```json
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2dpdGh1Yi5jb20vQWRhbUF1Z3VzdGluc2t5IiwidXBuIjoiZXN0dWRhbnRpbmRlckBxdWFya3VzLmlvIiwiZ3JvdXBzIjpbIlVzZXIiXSwiaWQiOjM1LCJleHAiOjE2MTgzNTIwNDIsImlhdCI6MTYxNzA1NjA0MiwianRpIjoiMTY5MzgwNTItMzliZS00NWFjLTk2YmMtM2ZkY2M5MjVjYTNhIn0.NM2viz9vD6hfrC5n9QtMg
```

### Response

```
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

Gives a like on the user whose ID was passed on the route

### AUTH

```json
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2dpdGh1Yi5jb20vQWRhbUF1Z3VzdGluc2t5IiwidXBuIjoiZXN0dWRhbnRpbmRlckBxdWFya3VzLmlvIiwiZ3JvdXBzIjpbIlVzZXIiXSwiaWQiOjM1LCJleHAiOjE2MTgzNTIwNDIsImlhdCI6MTYxNzA1NjA0MiwianRpIjoiMTY5MzgwNTItMzliZS00NWFjLTk2YmMtM2ZkY2M5MjVjYTNhIn0.NM2viz9vD6hfrC5n9QtMg
```

### Response

```json
201 - Created
```

## GET /students/matches

Shows the matches of the user whose JWT was passed

### AUTH

```json
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2dpdGh1Yi5jb20vQWRhbUF1Z3VzdGluc2t5IiwidXBuIjoiZXN0dWRhbnRpbmRlckBxdWFya3VzLmlvIiwiZ3JvdXBzIjpbIlVzZXIiXSwiaWQiOjM1LCJleHAiOjE2MTgzNTIwNDIsImlhdCI6MTYxNzA1NjA0MiwianRpIjoiMTY5MzgwNTItMzliZS00NWFjLTk2YmMtM2ZkY2M5MjVjYTNhIn0.NM2viz9vD6hfrC5n9QtMg
```

### Response

```json
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

### To see all routes, go to /q/swagger-ui
or use [openapi](https://swagger.io/specification/) file, accessing the /q/openapi route

## Ways to use



### Heroku

The easiest way to use the app is using the deploy done on heroku.

[Deploy at Heroku](https://estudantinder-api.herokuapp.com/)

## Local use

To use the application locally, both in development mode and when building the project, proceed to Installation

## Installation



You will need some tools to run the project locally. If you only want to use the api, see the topic Ways to Use

## Dependencies



### GIT

- Install git on your machine: [https://git-scm.com/downloads](https://git-scm.com/downloads)
- With git installed on your machine, clone the repository using the **Code** button at [https://github.com/Estudantinder/estudantinder-api](https://github.com/Estudantinder/estudantinder-api)
- If necessary, use a basic tutorial, like this one: [https://www.hostinger.com.br/tutoriais/tutorial-do-git-basics-introducao](https://www.hostinger.com.br/tutoriais/tutorial-do-git-basics-introducao)

### JDK 11

- Download and install the JDK 11 of your choice, for example the Oracle one: [https://www.oracle.com/java/technologies/javase-jdk11-downloads.html](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- To test whether the JDK was installed successfully, run the following command `java --version` in the terminal. The installed JDK version should appear.

### Maven

- Download the newest version of maven at [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
- If necessary use this tutorial [https://dicasdejava.com.br/como-instalar-o-maven-no-windows/](https://dicasdejava.com.br/como-instalar-o-maven-no-windows/)
- To test whether Maven is installed on your machine, run the `mvn -v` command. The installed version of Maven should appear.

### PostgreSQL

- For postgres it is recommended to use a docker, first install [Docker Desktop](https://www.docker.com/products/docker-desktop), then follow this simple tutorial [http://www.mariolb.com.br/blog/2020/09/19/postgresql_no_docker.html](http://www.mariolb.com.br/blog/2020/09/19/postgresql_no_docker.html)
- After installation, create the Estudantinder database with the command:

```json
CREATE DATABASE estudantinder
```

---

Well done! You can now start using the API, go to the [Scripts](https://github.com/Estudantinder/estudantinder-api#scripts) tab to see which scripts are available for use

## Available Commands



All of these scripts can be run on your machine using Maven

The script parameters will be represented with a `$` prefix

### quarkus:dev

To run the api in development mode, use the command

```json
./mvnw quarkus:dev
```

or

```json
mvn quarkus:dev
```

### quarkus:build

To build the app, use the command

```json
./mvnw quarkus:build
```

or

```json
mvn quarkus:build
```

At the end you will have an executable .jar in the /target folder

### test

To run the code tests, use the command

```json
./mvnw test
```

or

```json
mvn test
```

### To learn more about the commands available, visit [https://quarkus.io/guides/maven-tooling](https://quarkus.io/guides/maven-tooling)

## UI (Front End)

You can see the application backend in the following repository: [https://github.com/Estudantinder/estudantinder-mobile](https://github.com/Estudantinder/estudantinder-mobile)