# UserAPI

Hi folks! 
This repository contains the backend challenge implementation for software engineer vacancy at Perseus.

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

This migration can be performed using Flyway, and will create the schema and the tables necessary for the application. But first, you'll need to change the Database connection URL, user and password. To do this, first go to the ```application.properties``` file located at ```UserAPI/src/main/resources``` and change the ```DB_CONNECTION_URL```, ```DB_ÙSER``` and ```DB_PASSWORD``` to your database connection, user and password. This is important to the application be able to get the data from the correct database. You'll need to change these values on another file too, the ```flywayConfig.conf``` file. This file is located on the project's root directory (UserApi). Please update with the same values you placed in ```application.properties``` file. This file is used to perform the migration script.


### Application port setting
I used by default the port 8080 to run the application. If you want to use another port, please change it on the file ```application.properties``` located at the path ```UserAPI/src/main/resources```. 
The property you need to change is ```server.port```. 

### Installing project dependencies

1. To install the project dependencies, you'll need to have Maven installed. If you haven't Maven, please access this [tutorial](https://maven.apache.org/install.html) to install. 

2. On the command line, access UserApi directory (If you're on the repository folder userApi, please access the second level folder: UserAPI).

3. Execute the following command to install the project dependencies: ```mvn install```

4. The next step is generate the project ```.jar``` file. To do this, please execute the command: ```mvn clean package```

5. Now you can run the database migration that will create the schema and tables used on project. To do this, please execute the command: ```mvn compile flyway:migrate -Dflyway.configFiles=flywayConfig.conf```

6. With the generated package, you'll be able to run the project. To do this, execute the command: ```java -jar ./target/UserApi-0.0.1-SNAPSHOT.jar```


## A little more about the project

### Schema Model

To implement this API I created a schema called *userdb* and inside of it, I create three tables: *user*, *email* and *phoneNumber*.

The schema model is represented by the following image:

![schema_model](https://user-images.githubusercontent.com/19779830/150350424-a0e84558-474f-4bc0-ada0-ebadbfff9a70.png)

The *email* and *phoneNumber* have an many to one identifying relationship with *user*. When an user is deleted, their emails and phone numbers are also deleted.

### Application Architecture

I separated each layer of the application into packages:

![Screen Shot 2022-01-20 at 10 51 14](https://user-images.githubusercontent.com/19779830/150351579-308a3056-9ae9-481a-a860-c281e0066295.png)


- *Controller*: controllers are responsible to expose the endpoints to the API user be able to make requests. The controller layer communicates with the Service layer.
- *Dto*: data transfer objects created to expose only the important data on the request and is used on the controller layer. On this layer I also created some mappers to convert a dto object to an entity and vice versa.
- *Model*: data entities. Contains all properties present on entity's table on database.
- *Service*: responsible for some business logic, some exceptions are thrown here. The service layer communicates with the repository layer.
- *Repository*: used to communicate with the appliaction database and get/save the data.
- *Support*: some extra classes used on application. E.g: exceptions.

### Exceptions

I created some exceptions on the application that I judged to be consistent with the behavior expected by the API user. When these exceptions are triggered, the user receive a proper feedback message:

- **EmailDuplicatedException**: triggered when try to create or update an user with an email that already exists. Triggered when create or update email too. 
  - *Why I created this exception*: I believe that there may be users with the same first name and last name, here in Brazil at least it is quite common for people with common last names to have the same full names. That's why I found it necessary to check e-mail duplicity to avoid duplicate registration. The user can have more than one registered email but two users cannot have the same registered email.
- **EmailNotFoundException**: triggered when try to update an email that doesn't exists.
  - *Why I created this exception*: I created an API method that allows edit an email withouth editing the entire user. On this method an old email is requested. So it's important that the email to be replaced be an existing email.
- **InvalidEmailException**: triggered when try to create an invalid email. I'm using a library to validate the emails.
  - *Why I created this exception*: to avoid registration errors related to invalid email.
- **PhoneNumberNotFoundException**: triggered when try to update a phone number that doesn't exists.
  - *Why I created this exception*: I created an API method that allows edit a phone number withouth editing the entire user. On this method an old phone number is requested. So it's important that the phone number to be replaced be an existing phone number.
- **UserAlreadyHasTheEmailException**: triggered when try to add a email to an user that already has the same email registered.
  - *Why I created this exception*: to avoid one user to have more than one email with same value.
- **UserNotFoundException**: triggered when requested user was not found.
  - *Why I created this exception*: to give a feedback to API user that the requested user doesn't exists.

### Tests

I used ```JUnit``` to implement the application tests. In my tests I covered the *Service* and *Dto* layers.


### API Requests

I prepared a Postman Collection with all API request with example. To access the collection please access the project root folder (one folder before the current folder) and acces ```Postman_Collection``` directory. This directory contains the collection in ```.json```format that can be imported using Postman. But I'll explain a little more about each endpoint here:

#### User

The user requests are made by the following URL:

```http://localhost:8080/user/``` [1]

- Get user by id

To get an user by id, you'll have to make a ```GET``` request to the user URL(1) with the user id as a path variable:

```http://localhost:8080/user/1```

- Get user by first name

To get an user by first name, you'll have to make a ```GET``` request to the user URL(1) with the user's first name as a request parameter:

```http://localhost:8089/user?firstName=natalia```

The search for first name is case insensitive and will return a set with all users that have the wanted first name.

- Get user by last name

To get an user by last name, you'll have to make a ```GET``` request to the user URL(1) with the user's last name as a request parameter:

```http://localhost:8089/user?lastName=iassanori```

The search for last name is case insensitive and will return a set with all users that have the wanted last name.

- Create new user:

 To create a new user you'll have to make a ```POST``` request to the user URL(1) with the following body:
 
 ```
 {
    "firstName": "",
    "lastName": "",
    "emails": [
        {
            "mail": ""
        }
    ],
    "phoneNumbers": [
        {
            "number": ""
        }
    ]
} 
 ```
 
- Update user:

To update an user you'll have to make a ```PUT``` request to the user URL(1) with the following body:

```
{
    "id":  0,
    "firstName": "",
    "lastName": "",
    "emails": [
        {
            "mail": ""
        }
    ],
    "phoneNumbers": [
        {
            "number": ""
        }
    ]
}
```

It's important to put the user id in this request.

- Delete user

To delete a user you'll have to make a ```DELETE``` request to the user URL(1) with the user id as a path variable:

```http://localhost:8080/user/1```


#### Email

I created some additional requests to make possible to create and edit emails without having to edit the entire user.

The email requests are made by the following URL:

```http://localhost:8080/email/``` [2]

- Create email

To create a new email you'll have to make a ```POST``` request to the email URL(2) with the following body:

```
{
    "userId": 0,
    "newMail": ""
}
```

It's important to put the user id in this request.

- Update email

To update email you'll have to make a ```PUT``` request to the email URL(2) with the following body:

```
{
    "userId": 0,
    "oldMail": "",
    "newMail": ""
}
```

I created this method to be possible to edit an existing email with another value. So, it's important to put the user id and the email that will be replaced with the new value.

#### Phone Number

I created some additional requests to make possible to create and edit phone numbers without having to edit the entire user.

The phone number requests are made by the following URL:

```http://localhost:8080/phoneNumber/``` [3]

- Create phone number

To create a new phone number you'll have to make a ```POST``` request to the phone number URL(3) with the following body:

```
{
    "userId": 0,
    "newNumber": ""
}
```

It's important to put the user id in this request.

- Update phone number

To update phone number you'll have to make a ```PUT``` request to the phone number URL(3) with the following body:

```
{
    "userId": 0,
    "oldNumber": "",
    "newNumber": ""
}
```

I created this method to be possible to edit an existing phone number with another value. So, it's important to put the user id and the phone number that will be replaced with the new value.
