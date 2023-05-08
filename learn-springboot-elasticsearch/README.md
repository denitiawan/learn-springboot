# Create Rest API using Springboot and MongoDB

### Introduction
On this repo we will learn how to create REST API using Springboot, 
that apis will have CRUD function for connecting, storing and searching data to Elastic Search. 

If you want read the full articles all about this repo, you can see on this article [article](https://medium.com/@denitiawan/create-rest-api-using-springboot-for-searching-data-to-elastic-search-8ee3c0fa2ec9)

### Requirements
- Maven 3+
- Java 8+
- IDE Intelij
- Docker
- Elastic Search (Docker Container)
- Postman 

### Dependency
- Lombok
- Elastic Search Driver
- Spring Data JPA
- Spring Web

### Postman Api Collection
- project/document/postman-collection/medium/springboot-api-collection.postman_collection.json

### Docker Compose file
- project/document/server/docker-compose.yml

### Notes
- project/document/server/notes.md



### APis
| Method | URL APIs                                 | Description              | Client  |
|--------|------------------------------------------|--------------------------|----------|
| POST   | localhost:8181/api/elastic/product   | save or update  | Postman  |
| DELETE | localhost:8181/api/elastic/product/{id}    | delete by   | Postman  |
| GET    | localhost:8181/api/elastic/product/{id}    | search by id  | Postman  |
| GET    | localhost:8181/api/elastic/product    | search all  | Postman  |




