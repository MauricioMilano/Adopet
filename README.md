# Adopet API - Schema
Api feita em SpringBoot para aplicação de adoção de Pets. O chamado Adopet é um software de adoção de pets. Plugue seu front end e ajude a tirar cada vez mais pets da rua


**POST**

**Cadastro**

**Path:** */api/auth/signup*

- Request: 

```json
{
  "username": "admin",
  "email": "admin@email.com",
  "password": "123456",
  "picture": "data:image/jpeg;base64,",
  "role": [
    "admin",
    "user"
  ]
}
```

- Response: 

```json
{
  "message": "User registered successfully!"
}
```

**POST**

**Login**

**Path:** */api/auth/signin*

- Request: 

```json
{
  "username": "admin",
  "password": "123456"
}
```
- Response: 

```json
[
  {
    "id": 5,
    "username": "admin",
    "email": "admin@email.com",
    "roles": [
      "ROLE_USER",
      "ROLE_ADMIN"
    ],
    "tokenType": "Bearer",
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY0MzU5NzUwOSwiZXhwIjoxNjQzNjgzOTA5fQ.zKkzb5LVVnUgL7-Fk0jNaSqyRrnQOJT188-KYzhXsj5wLVz9FDAnfUmpavidQ7djy7xYrSsgosmGR6HCYNBp6w"
  }
]
```


**GET**

**Publicacoes**

**Path:** */publications*

**Authorization:** bearer token

- Request: 

```json

```

- Response: 

```json
[
  {
    "id ": 1,
    "description": "Muito am├ível, pode adotar",
    "address": "rua das couves",
    "zipcode": "24220-420",
    "pets": [
      {
        "name": "Lya",
        "pics": "data:image/jpeg;base64",
        "age": "7"
      }
    ],
    "user": {
      "id": 5,
      "username": "admin",
      "picture": "data:image/jpeg;base64",
      "email": "admin@email.com",
      "roles": [
        {
          "id": 1,
          "name": "ROLE_USER"
        },
        {
          "id": 2,
          "name": "ROLE_ADMIN"
        }
      ]
    }
  }
]
```

**POST**

**publications**

**Path:** */publications*

**Authorization:** bearer token


- Request: 

```json
{
  "description": "Muito am├ível, pode adotar",
  "address": "rua das couves",
  "zipcode": "24220-420",
  "pets": [
    {
      "name": "Lya",
      "pics": "data:image/jpeg;base64",
      "age": "7"
    }
  ]
}
```

- Response: 

```json
{
  "id ": 1,
  "description": "Muito am├ível, pode adotar",
  "address": "rua das couves",
  "zipcode": "24220-420",
  "pets": [
    {
      "name": "Lya",
      "pics": "data:image/jpeg;base64",
      "age": "7"
    }
  ]
}
```

**GET**

**Likes**

**Path:** */like/{publication_id}*




| Param          | Description       |
| -------------- | ----------------- |
| publication_id | Id da publicação  | 


**Authorization:** bearer token

- Request: 

```json

```

- Response: 

```json
{
  "qtdLikes": 0,
  "youLiked": false
}
```
**POST**

**Dar Like**

**Path:** */like/{publication_id}*

| Param          | Description       |
| -------------- | ----------------- |
| publication_id | Id da publicação  | 

**Authorization:** bearer token

- Request: 

```json

```

- Response: 

```json
{
  "qtdLikes": 1,
  "youLiked": true
}
```

**GET**

**Ver comentarios por publicacao**

**Path:** */comment/{publication_id}*

| Param          | Description       |
| -------------- | ----------------- |
| publication_id | Id da publicação  | 

**Authorization:** bearer token

- Request: 

```json

```

- Response: 

```json
[
  {
    "id": 4,
    "content": "bonito cachorro",
    "user": {
      "id": 5,
      "username": "admin",
      "picture": "data:image/jpeg;base64",
      "email": "admin@email.com",
      "roles": [
        {
          "id": 1,
          "name": "ROLE_USER"
        },
        {
          "id": 2,
          "name": "ROLE_ADMIN"
        }
      ]
    }
  }
]
```

**POST**

**Postar Comentario**

**Path:** */comment/{publication_id}*


| Param          | Description       |
| -------------- | ----------------- |
| publication_id | Id da publicação  | 


**Authorization:** bearer token

- Request: 

```json
{
  "message": "bonito cachorro"
}
```

- Response: 

```json
{
  "id": 4,
  "content": "bonito cachorro",
  "user": {
    "id": 5,
    "username": "admin",
    "picture": "data:image/jpeg;base64",
    "email": "admin@email.com",
    "roles": [
      {
        "id": 1,
        "name": "ROLE_USER"
      },
      {
        "id": 2,
        "name": "ROLE_ADMIN"
      }
    ]
  }
}
```

**POST**

**Enviar mensagem no chat**

**Path:** */chat/{user_id}*

| Param          | Description   |
| -------------- | ------------- |
| user_id | Id do usuário | 

**Authorization:** bearer token

- Request: 

```json
{
  "content": "Ola, tenho interesse em adotar o seu pet"
}
```

- Response: 

```json
{
  "id": 1,
  "date": "2022-01-31",
  "content": "Ola, tenho interesse em adotar o seu pet ",
  "userTo": {
    "id": 9,
    "username": "Maria",
    "picture": "data:image/jpeg;base64,",
    "email": "maria@email.com",
    "roles": [
      {
        "id": 1,
        "name": "ROLE_USER"
      }
    ]
  },
  "userFrom": {
    "id": 5,
    "username": "admin",
    "picture": "data:image/jpeg;base64,",
    "email": "admin@email.com",
    "roles": [
      {
        "id": 1,
        "name": "ROLE_USER"
      },
      {
        "id": 2,
        "name": "ROLE_ADMIN"
      }
    ]
  }
}
```

**GET**

**recuperar mensagens com o usuario especifico**

**Path:** */chat/{user_id}*


| Param          | Description   |
| -------------- | ------------- |
| user_id        | Id do usuário | 


- Request: 

```json

```

- Response: 

```json
[
  {
    "id": null,
    "date": "2022-01-26",
    "content": "Oi",
    "userTo": {
      "id": 3,
      "username": "usuario",
      "picture": "data:image/jpeg;base64,",
      "email": "usuario@email.com",
      "roles": [
        {
          "id": 1,
          "name": "ROLE_USER"
        }
      ]
    },
    "userFrom": {
      "id": 5,
      "username": "admin",
      "picture": "data:image/jpeg;base64,",
      "email": "admin@email.com",
      "roles": [
        {
          "id": 1,
          "name": "ROLE_USER"
        },
        {
          "id": 2,
          "name": "ROLE_ADMIN"
        }
      ]
    }
  }
]
```

**GET**

**Profile**

**Path:** */profile*

**Authorization:** bearer token

- Request: 

```json

```
- Response: 

```json
{
  "id": 5,
  "username": "admin",
  "picture": "data:image/jpeg;base64,",
  "email": "admin@email.com",
  "roles": [
    {
      "id": 1,
      "name": "ROLE_USER"
    },
    {
      "id": 2,
      "name": "ROLE_ADMIN"
    }
  ]
}
```
