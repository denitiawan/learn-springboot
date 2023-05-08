# restapi-springboot-redis

##  install redis on windows or on docker container
### install redis on windows
see file guide on older document/install on window

### install redis on docker container with docker-compose.yml
see file guide on document/docker

### how to open redis-cli on docker container
- open redis cli
  docker container exec -it server_redis  redis-cli -h 127.0.0.1 -p 6379 -a "password123"
- show all key
  keys *
- get data by has keys : get <keys>
  get token : get token value
  get user  : get user value
  ttl user  : show expired time for user value

## add dependency redis
add dependency on pom.xml
--------------------------------- 
         <!-- redis-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-redis</artifactId>
        </dependency>

        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
        </dependency>


        <!-- jpa-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
---------------------------------

## add redis properties on application.properties
---------------------------------
# redis with auth basic
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.password=password123
spring.redis.database=0
spring.redis.timeout=10
---------------------------------

## configuration and implement redis
- crete redis config : RedisConfig
- create controller : RedisController
- create service : RedisServerService
- create repo : RedisRepo
- RedisAbstractService  : for standarization all function will needed by Other RedisService, like (UserRedisService, RoleRedisService ...)
- RedisAbstractContract : function contract will needed by RedisAbstractService
- RedisAbstractController : for standarization all function awill needed by Other RedisController (UserRedisController, RoleRedisController...)


## Test API add data to redis server
### add or update data to redis
- url
  http://localhost:8181/v1/redis/saveupdate
- req body
  {
  "key":"x-auth-token",
  "value":"12345-648324923-3488034923432-2394238432"
  }

### search redis by haskey = x-auth-token
- url
  http://localhost:8181/v1/redis/find/x-auth-token
- response
  {
  "code": 200,
  "status": "200 OK",
  "message": "data redis found",
  "data": {
  "key": "x-auth-token",
  "value": "\"000000-1111111-2222222-9012830123-deni\""
  }
  }

### delete redis by haskey = x-auth-token
- url
  http://localhost:8181/v1/redis/delete/x-auth-token
- response
  {
  "code": 200,
  "status": "200 OK",
  "message": "Delete success",
  "data": "x-auth-token"
  }


