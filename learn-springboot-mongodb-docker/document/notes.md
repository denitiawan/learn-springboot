# restapi-springboot-mongodb

## create  docker container for mongodb & mongo express
- location of docker compose file for create and run container
  ./document/server/docker-compose.yml
- how to create and run container mongodb and mongo-express client
  docker-compose up -d

## create database, collection, and insert new data user to mongodb
- open the container server_mongodb
  docker container exec -it server_mongodb bash
- login to mongo db
  mongosh -u root -p password -host localhost -port 27017
- create database db_mongo
  use <databasename>  : use db_mongo
- create collection user : 
  run syntax create collection user on file (./database/db_mongo) and open it 

## syntax on mongo db
- function show database
- function use database
- function show collection
- function save & update
- function update
- function delete
- function select all
- function select equal
- function select like
- function select with or
- function select with and

## springboot crud to mongo db
- open pom.xml ,add dependecy : spring data mongo & spring jpa
--
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>

--

- add mongo config on application properties
# mongo db
spring.data.mongodb.host=127.0.0.1
spring.data.mongodb.port=37018
spring.data.mongodb.authentication-database=admin
spring.data.mongodb.username=root
spring.data.mongodb.password=password
spring.data.mongodb.database=db_mongo

--

- on MainApp add annotation:
  @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
- create controller with mapping (/v1/mongo/user) and endpoints (/save, /all, /find/{id}, /delete/{id})
- create service
- creae repository extend to MongoRepository
- create collection class
  User.java, Role.java, Access.java
- on collectoin class add annotation:
  @ToString
  @Document(collection = "user")
  
# testing api and check data on mongodb
- test api on postman
- check data on mongodb

