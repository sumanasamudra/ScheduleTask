# ScheduleTask
Task Scheduler

The implementation of DAO layers that provide CRUD functionality on JPA entities can be a repetitive, time-consuming task that we want to avoid in most cases. Luckily, Spring Boot makes it easy to create CRUD applications through a layer of standard JPA-based CRUD repositories.

In this tutorial, we'll learn how to develop a CRUD web application with Spring Boot and Thymeleaf.

# Start a Spring Boot Project
First generate your Spring Boot application with (at least) these five dependencies:

1. Spring Web
Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
2. Spring Data JPA
Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
3. Thymeleaf
A modern server-side Java template engine for both web and standalone environments. Allows HTML to be correctly displayed in browsers and as static prototypes.

4. JDBC Driver
A JDBC  driver that allows Java programs to connect to a PostgreSQL database using standard, database independent Java code.

# SQL Script
Create DB schema #task and change property file with valid database credentials 

# application.properties

server.port=9000
server.servlet.session.timeout=1m	

logging.level.root=info
logging.pattern.console=%d{dd-MM-yyyy HH:mm:ss.SSS} %magenta([%thread]) %highlight(%-5level) %logger.%M - %msg%n

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/task
spring.datasource.username=
spring.datasource.password=

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
security.basic.enabled=false

# MySQL query to create tables
CREATE TABLE `role` (
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`name`)
);

CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` longtext NOT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY  (`user_email`),
  CONSTRAINT  FOREIGN KEY (`user_email`) REFERENCES `user` (`email`)
) ;

CREATE TABLE `user` (
  `email` varchar(255) NOT NULL,
  `active` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `valitate_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ;

CREATE TABLE `user_roles` (
  `user_email` varchar(255) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  KEY  (`role_name`),
  KEY (`user_email`),
  CONSTRAINT  FOREIGN KEY (`role_name`) REFERENCES `role` (`name`),
  CONSTRAINT FOREIGN KEY (`user_email`) REFERENCES `user` (`email`)
) ;

#  1 The Maven Dependencies
Refere pom.xml 
#  2  The Domain Layer
This layer will include one single class which will be responsible for modeling User entities: User, Task, Role - I've annotated the class with the @Entity annotation.
# 3 The Repository Layer
Spring Data JPA allows us to implement JPA-based repositories (a fancy name for the DAO pattern implementation) with minimal requirement: User Repository, Task Repository - annotated by @Repository.
# 4 The Controller Layer
The controller class relies on some of Spring MVC's key features.
Let me start with the controller's IndexController( @GetMapping("/login") 
	public String showLoginForm() method) 
	The former will display the user signup form, while the latter will persist a new entity in the database after validating the constrained fields. (Default Users are created could directly login without registartion)
 # 5 The View Layer
 Under the src/main/resources/templates folder we need to create the HTML templates required for displaying the signup form, the update form, and rendering the list of persisted User entities.
 # 6 Running the Application
 Finally, let's define the application's entry point. Like most Spring Boot applications, we can do this with a plain old main() method:

@SpringBootApplication
public class demo  implements  CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
Now, let's hit “Run” in our IDE, then open up our browser and point it to http://localhost:9000

# Default user details to login 

  User name        ---      Password
  
1 "admin@mail.com"    "admin1234admin!" 

2 "user1@mail.com"     "user1234"

3 "user2@mail.com"     "user1234user!"


# Additional features and security functionality of the application
1. Encode the Password on Registration or save encoded password in database:
Encode the Password on Authentication using # BCryptPasswordEncoder
2. Role based authorization - Admin role can delete or view all the users
Refer SecurityConfig.java class- 
http.authorizeRequests().antMatchers("/register", "/", "/about", "/login","/confirm", "/css/**", "/webjars/**").permitAll()
				.antMatchers("/profile").hasAnyRole("USER,ADMIN")
				.antMatchers("/users1").hasRole("ADMIN")
				.antMatchers("/users","/addTask").permitAll()

 


