# Ukrposhta
___
## Description
Ukrposhta is a RESTful web application that supports CRUD operations. <br>
Created using Java Spring Boot with H2 in-memory ***relational database***.

## Structure
|      Module       |                Description                |
|:-----------------:|:-----------------------------------------:|
|    controller     |       CRUD operations with database       |
|        dto        | Classes for http requests and responses   |
|     exception     |        Custom exceptions handlers         |
| inject            |            Injecting test data            | 
|  lib              |    E-mail, full name & date validation    |
|      mapper       | Mapping between DTOs and model objects    |
|       model       |   Representing entities in the database   |
|    repository     | Classes to access data in the database    |
|      service      |     Classes to perform business logic     |
|       util        |             DateTime Pattern              |
| application       |           Ukrposhta Application           |
|     resources     | File application.properties with database |
|                   |         and Hibernate properties          |
|      pom.xml      |       Dependencies & other settings       |

## Used technologies
Java 17, Spring Boot, Spring Web MVC, Spring Data JPA <br>
Hibernate, H2, Postman, JUnit 5, Mockito, Maven, Lombok<br>
## How to run the application?
- Install Intellij IDEA, JDK 17 or higher on your computer<br>
- Clone this repository with command in terminal:<br>
`git clone git@github.com:OleksandrKostiukevych/ukrposhta.git`<br>
and open it in your IDE<br>
- run the app<br>
## About application
N-tier architecture (MVC pattern) has been used while building this app.<br>
Database is normalized to the third normal form.<br>
There are **four models**: programmer & manager (both extend class employee, <br>
which contains common fields for programmer & manager classes),<br> 
project & technology. Joined table JPA inheritance strategy has been <br>
used here. That's why database consists of such **tables** as:<br>
employees, managers, programmers, projects, technologies, <br>
programmers_technologies, projects_managers, projects_programmers.<br>
**Employee** class has such common fields as: id, fullName (String), <br> 
email (String), gender (Enum), birthDate (LocalDate), hiringDate (LocalDate),<br>
salary (BigDecimal), status (Enum).<br>
**Programmer** class has such specific fields as: level (Enum: ***Jun/Mid/Senior***),<br>
type (Enum: ***developer/QA/DevOps/***), id.<br>
**Project** class has such fields as: id, deadline (LocalDate), description<br>
(String), status (Enum: new/execution/finished).<br>
**Technology** class has only id and name (String) fields.<br>
Either manager or programmer can participate in several projects, that's why<br>
**many-to-many** relation is used here.<br>
Custom **Global Exception Handler** Exception and some additional Exception<br> 
Handlers are implemented here.<br>
PostConstruct method was used in InjectController class to inject test data.<br>
