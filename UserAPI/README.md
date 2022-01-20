# UserAPI

Hi folks! 
This repository contains the backend challenge implamentation for software engineer vacancy at Perseus.

## Technologies
I used the following technologies to build the application:

- Java 8
- Maven
- Spring Boot
- Flyway
- Spring Data JPA
- JUnit
- MySQL

## First Settings

First of all, please clone this project to your machine!

### Database settings - important!

I used MySQL as the database of my application. I created a migration file which is located in the path: 

```UserAPI/src/main/resources/db.migration``` 

This migration can be performed using Flyway, and will create the schema and the tables necessary for the application. But first, you'll need to change the Database connection URL, user and password. To do this, first go to the ```application.properties``` file located at ```UserAPI/src/main/resources``` and change the ```DB_CONNECTION_URL```, ```DB_ÙSER``` and ```DB_PASSWORD``` to your database connection, user and password. This is important to the application be able to get the data from the correct database. You'll need to change these values on another file too, the ```flywayConfig.conf```file. this file is located on the project's root directory. Please update with the same values you placed in ```application.properties``` file. This file is used to perform the migration script.


### Application port setting
I used by default the port 8080 to run the application. If you want to use another port, please change it on the file ```application.properties``` located at phe path ```UserAPI/src/main/resources```. 
The property you need to change is ```server.port```. 

### Installing project dependencies

1. To install the project dependencies, you'll need to have Maven installed. If you haven't Maven, please access this [tutorial](https://maven.apache.org/install.html) to install. 

2. On the command line, access UserApi directory (If you're on the reporitory folder - userApi, please access the second level folder: UserAPI).

3. Execute the following command to install the project dependencies: ```mvn install```

4. The next step is generate the project ```.jar``` file. To do this, please execute the command: ```mvn clean package```

5. Now you can run the database migration that will create the schema and tables used on project. To do this, please execute the command: ```mvn compile flyway:migrate -Dflyway.configFiles=flywayConfig.conf```

6. With the generated package, you'll be able to run the project. To do this, execute the command: ```java -jar ./target/UserApi-0.0.1-SNAPSHOT.jar```


## A little more about the project

### Schema Model

To implement this API I created a schema called *userdb* and inside of it, I create three tables: *user*, *email* and *phoneNumber*.

The schema model is represented by the following image:


