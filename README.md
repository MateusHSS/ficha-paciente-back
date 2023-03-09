# Pré requisitos

- Java 11 [Download](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
- Docker [Download](https://www.docker.com/)
- Maven
- Spring boot 2.7.9

Projeto base: [Download](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.7.9&packaging=jar&jvmVersion=11&groupId=com.api&artifactId=ficha-paciente&name=ficha-paciente&description=Sistema%20hospitalar%20de%20valida%C3%A7%C3%A3o%20de%20fichas%20de%20pacientes&packageName=com.api.ficha-paciente&dependencies=web,validation,sqlserver,data-jpa)

## Dependências do projeto

- Spring Web
- Validation
- MS SQL Server Driver
- Spring Data JPA

# Banco de dados

Para rodar o banco de dados SQL Server, o sistema usa o Docker, portanto para criar a instancia do banco, entre na pasta do projeto pelo terminal e execute:

```
docker build -t sql-server-db .
```

Após o fim do build, para iniciar o container com a instância do banco, execute:

```
docker run -d sql-server-db
```

# Inicializando API

Para inicializar a API, entre na pasta do projeto pelo terminal e execute:

```
./mvnw spring-boot:run
```

O servidor da aplicação será inicializado
