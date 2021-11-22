# Introdução ao Projeto ToDo

### Documentação referente

Tecnologias utilizadas:

* [Java 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)
* [PostgreSQL 10](https://www.postgresql.org/download/)
* [Maven](https://maven.apache.org/)
* [Tomcat](http://tomcat.apache.org/)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.0/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.0/reference/htmlsingle/#using-boot-devtools)

### Guides

O Seguintes Endpoint foram implementados:

* Cadastrar Usuário: POST http://localhost:8080/users
* Listar Usuários: GET http://localhost:8080/users
* Consultar Usuário: GET http://localhost:8080/users/{id}
* Listar Tarefas: GET http://localhost:8080/tasks
* Listar Tarefas do usuário: GET http://localhost:8080/users/{idUser}/tasks
* Cadastrar Tarefa do usuário: POST http://localhost:8080/users/{idUser}/tasks
* Atualizar Tarefa do usuário: PUT http://localhost:8080/users/{idUser}/tasks/{taskId}
* Excluir Tarefa do usuário: DELETE http://localhost:8080/users/{idUser}/tasks/{taskId}
* Associar uma Tarefa a outra: GET http://localhost:8080/tasks/{idUser}/attach/{taskId}
* Desassociar uma Tarefa a outra: GET http://localhost:8080/tasks/{idUser}/attach/{taskId}

### Implantação para Teste (Sugestão)

* Contratar 2 Droplet na Digital Ocean, 1 para aplicação Backend e outra para o banco de dados
* Em uma das intâncias, configurar o tomcat no servidor para comportar o código
* Montar um Droplet em Marketplace com a opção Banco de Dados Postgres
* No projeto Web atualizar o arquivo application.properties do spring com as informação do 
banco de dados
