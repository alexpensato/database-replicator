# Database-Replicator

*Spring Boot example project to work with multiple databases*

## Introduction

Many times you need to replicate a database, but you may be lacking propor tooling from your vendor or license restrictions may forbid you to create a cluster.
With this simple, yet powerful, spring boot example, you will be able to overcome this difficulties.

Here are a few (common) reasons to use the Database-Replicator:

* Test a new relational database management system (RDBMS) using real data from a live system
* Test non-relational databases (NO-SQL) using real data from a live system
* Easily create a separate database with all necessary data for testing purposes (even changing or masking some attributes)
* Easily change core database configurations like encoding, collation and other stuff that your vendor requires full-reinstallation
* Create a load balancing system using different RDBMS's (even mixing licensed and open-source products)
* And much more!

## Hands-on

1. Create a Spring Boot project

The easiest way to create a new project is going to [start.spring.io](https://start.spring.io/) and filling your preferences.

Select **Gradle project** and add these dependencies: *Spring Data JPA, Vaadin, Lombok and PostgreSQL driver*.

Although we won't need a Web project, Vaadin is added here to create the default Gradle project structure and Application class.

Download the zip file and unzip it in your workspace directory.

2. Importing the project in your IDE

Using IntelliJ (or your favorite IDE), go to ```File > New > Project from Existing Sources...```

Select **Gradle model** and then **Gradle wrapper**.

Make sure you have the correct JDK selected.

3. Install Lombok plugin

Lombok makes it easy to create complex Entity classes and we are going to take advantage of that.

After IntelliJ has started, go to ```File > Settings...```

First go to ```Plugins```, search for Lombok and Install it.

Then go to ```Builder, Execution, Deployment > Compiler > Annotation Processors``` and enable annotation processing for this project.

Now you need to Restart the IDE.

4. Configure MariaDB driver

You need to check the latest version of the driver in the [maven repository website](https://mvnrepository.com/artifact/org.mariadb.jdbc/mariadb-java-client).

At the moment, the latest version is 2.4.2. So simply add the line below to your ```build.gradle``` file:

```runtimeOnly 'org.mariadb.jdbc:mariadb-java-client:2.4.2'```

5. Make sure you have both PostgreSQL and MariDB installed on your machine.

You can use PgAdmin III to create a user and database for PostgreSQL, and HeidiSQL to create them for MariaDB.

Here are a few helpful commands for MariaDB:

```SQL
CREATE DATABASE `replicator` /*!40100 COLLATE 'utf8_general_ci' */;
CREATE USER 'replicator'@localhost IDENTIFIED BY 'replicator';
GRANT ALL PRIVILEGES ON `replicator`.* TO 'replicator'@localhost;
```

6. Exclude database's auto configuration classes

In the ```ReplicatorApplication``` class, add exclusions to auto confiogurations and enable transaction manager:

```java
@SpringBootApplication(
		exclude = { DataSourceAutoConfiguration.class,
				HibernateJpaAutoConfiguration.class,
				DataSourceTransactionManagerAutoConfiguration.class })
@EnableTransactionManagement
public class ReplicatorApplication {
    // ...
}
```

7. Create **packages** for *models, repositories, configs and services*.

Just follow the pattern from this github source.

8. Create **entities** according to the ```models``` package in this github repository.

It is important to notice that there are quite a few annotations in each entity class.

Explaining them are beyond the scope of this tutorial. You can learn more about **Lombok** and **JPA** from a variety of other sources.

9. Create **repositories interfaces** according to the ```repositories``` package in this github repository.

Because we are going to reuse Entities classes, we had to replicate repositories for each RDBMS.

Spring Data makes it easy to reuse them.

10. Create **datasource configurations** according to the ```config``` folder.

Beware that in this case there are minor differences between the Postgres and MariaDb Config classes.

The Postgres will use the ```create.sql``` file in the ```resources``` folder.

11. Create the **service** class according to the ```service``` folder.

Here we take advantage of the ```@AllArgsConstructor``` of the **Lombok** project to avoid Spring annotation injection.

We also have replicated methods here for the **origin database** (*PostgreSQL*) and the **destination db** (*MariaDB*).

12. Create a **load** method in the ```ReplicatorApplication``` class.

This is the last step before running the project. We will copy all data from the PostgreSQL to MariaDB using the same entities classes.

Now you can execute Spring's **bootRun** task and see the magic happen!

## Contributions

…are always welcome.
Don’t hesitate to submit a [bug report](https://github.com/alexpensato/database-replicator/issues) or 
a [pull request](https://github.com/alexpensato/database-replicator/pulls).

When filling a bug report or submitting a new feature, please try including supporting test cases.


## License

This project is licensed under [Apache License 2.0](http://www.apache.org/licenses/).

Although I have run this project successfully in several occasions, use at your risk!
