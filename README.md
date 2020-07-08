# 📝 TODO Application
This is an application to manage todo items. As of now it only has a function to add a todo item. You can create a separate branch forked from `master`and then submit a pull request having changes made by you. 
### ⚙ Tech used
- Java / Maven / Spring Boot
- Docker
- PostgreSQL

### 💻 Pre-requisite softwares
- Docker
- Java SDK 8

### 🏃‍ Steps to run the app using Docker
- Running `docker-compose up` :
    - Both Database and app server will start up and then you can call the APIs by using `Postman`
    
### 🏃‍ Steps to run the app without using Docker
- Install postgresDB locally / use DB by running `docker-compose up`
- Run command `./mvnw spring-boot:run`

### 🏃‍ Steps to run the test
- Run `docker-compose up`
- Run `docker exec -it todo_app_1 bash`
- Run `mvn test`

