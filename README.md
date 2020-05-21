# G2G Lite (API REST JDK 1.8 + JPA/Hibernate + MySQL Spring Security/JWT + Gmail + Amazon S3 + Ionic + Cordova)

App focused on manage all basic features of shopping through a hybrid solution involving both frontend (Ionic + Cordova) and backend (API rest using JDK 1.8).

## Author

**Alexandre Antonio Lana Rosseto** 
* *Initial work* - [wbshopping](https://github.com/alexandrerosseto/wbshopping) (GitHub)
* Released on https://wbshopping.herokuapp.com (Heroku)
* My professional profile on [LinkedIn](https://www.linkedin.com/in/alexandrerosseto)

## License

This project is free to use and it was designed for demonstration purposes.

## Expectations

This project was designed to demonstrate:

* Java EE
* Object-relational mapping with JPA/Hibernate
* Use of Spring Boot libraries
* Swagger
* Lambda applications
* Knowledge of Exceptions hierarchy and inheritance
* Application of custom exceptions
* Use of H2 database for supporting develop
* MySQL database
* Spring Security / JWT
* Frontend: Ionic (Angular/TypeScript) + Cordova
* Provisioning with Heroku and publishing on Play Store

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them.

```
* Java 1.8
* Spring framework 2.2.6
* Swagger 2.9.2
* Maven
* H2 database
* PostgreSQL
* Heroku CLI
```
Recomended complementary tools.

```
* [STS](https://spring.io/tools)
* [Postman](https://www.postman.com)
```

### Installing

A step by step series of examples that tell you how to get a development environment running.


**Installing and Configuring H2 Database**

```
* Checklist
	- Make sure that JPA and H2 dependencies are in place
	- For usage of H2 database, check if the application.properties file is set to "test" in the section profile.
	- Make sure application-test.properties file is available in the project
```

**Dependencies**

```
The following dependencies should be in the POM file.

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>runtime</scope>
</dependency>
```

**Provisioning Production Environment On Heroku**
 
```
If you would like to create your own environment on Heroku, please use the checklist below.

Creating A New APP
	* Login in your account on [Heroku](https://www.heroku.com) or sign up
	* From your [Dashboard](https://dashboard.heroku.com/apps), click on NEW and choose name and location for your app
Provisioning A PostgreSQL Database
	* Click on the app you just created and find Resource tab
		* Go to dashboard -> Resources 
		* Search "postgres" on Add-ons -> select "Heroku Postgres" -> choose your plan

```

**Installing PostgreSQL**

```
* Download and install: https://www.postgresql.org/download/ 
	○ Super user: postgres 
	○ Password: 1234567 
	○ Port: 5432 
* Start/stop service: Task manager -> Services 
* Check instalation 
	○ Start pgAdmin 
	○ Databases -> Create -> Database 
	○ Encoding: UTF8 
```

**Load Data Into PostgreSQL Provisioned**

```
 	* Access your app on Heroku
	* Tab dashboard -> Settings -> CONFIG VARS and edit the connection string as follows: 
		○ Remove "postgres://" and split the text 
		○ [USER] remove ":"
		○ [PASSWORD] remove "@"
		○ [SERVER] remove ":"
		○ [PORT] remove "/"
		○ [DATABASE]
	* PgAdmin: Servers -> Create -> Server
		○ Advanced -> DB rescriction: (database)  
	* Database -> Query Tool  
		○ Load and run SQL Script wbshopping.sql provided in the source code.
```

## Running the tests

This project is been released without JUnit tests because it was designed intended to show knowlegde related to the items described on the section Expectations.

Please see [Spring-boot-mongodb](https://github.com/alexandrerosseto/spring-boot-mongodb) to have a demonstration on how to use JUnit for integration tests.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli#download-and-install) - To deploy on Heroku