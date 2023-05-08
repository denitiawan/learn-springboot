### add liquibase dependecy on pom.xml
```
<!-- liquibase -->
<dependency>
    <groupId>org.liquibase</groupId>
    <artifactId>liquibase-core</artifactId>
</dependency>
```

### create changelog-master.yml
- open folder ./main/resource/
- create folder db/changelog
- create file changelog-master.yml
```
databaseChangeLog:
- include:
  file: v1x/index.yml
  relativeToChangelogFile: true
```
changelog-master.yml is used for registered the index.yml on changes version folder

### add version folder for changes schema
- open folder ./main/resource/db/changelog
- create folder v1x 


### create file index.xml
- open folder ./main/resource/db/changelog/v1x/
- create file index.xml
```
databaseChangeLog:
  - include:
      file: 00001_init_db.yml
      relativeToChangelogFile: true
```
index.yml is used for registered all changes .yml files on folder version

### create new database, database name must same with application properties
```
create database `database`
```

### run project
```
mvn spring-boot:run 
```
steps when project starting
- mvn will clean install
- liquibase will : create table DATABASECHANGELOG
- liquibase will : create table DATABASECHANGELOGLOCK
- liquibase will : run all changes .sql files

### see diagram documentation on
```
./main/doc/liquibase/liquidbase arsitektur.drawio
```



 
