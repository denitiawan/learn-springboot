# restapi-springboot-elastic

### install elastic on docker container with docker-compose.yml

## add dependency elastic
add dependency on pom.xml
--------------------------------- 
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
            <version>2.7.1</version>
        </dependency>
        <dependency>
            <groupId>jakarta.json</groupId>
            <artifactId>jakarta.json-api</artifactId>
            <version>2.1.0</version>
        </dependency>
---------------------------------

## add custom eleastic properties on application.properties
---------------------------------
application.elastic.host=localhost
application.elastic.port=9200
---------------------------------


## configuration and implement elasticsearch
on pacakge elasticsearch, will store all about elastic stuff like:
- config = for config elastic search
- controller = all controller for operation  elastic
- document = for store the index elastic
- dto = for request body and response body controller
- repo = for query elastic search
- service = logic bussines function
- ElasticConstant.java = for static variable using on elastic pacakge only

## Test API Elastic Search
### create product
- url
  http://localhost:8181/api/elastic/product/saveupdate
- req body
  {
  "id":"1",
  "name":"Brakeset TRP G Slate",
  "description":"Brakeset TRP G Slate",
  "price":30000
  }

### search product by id
- url
  http://localhost:8181/api/elastic/product/1

### search all product
- url
  http://localhost:8181/api/elastic/product


## Test Server Elastic
### show all data on index 'pos-product'
- url
  http://localhost:9200/app-product/_search


## learn Elastic Query
https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-query-string-query.html

## Add Basic Authentication on Elastic Server
for protect the elastic server, see the referense
https://stackoverflow.com/questions/50832249/enable-authentication-in-elasticsearch-with-docker-environment-variable





