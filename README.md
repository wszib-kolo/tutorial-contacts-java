# Steps

## Step `0-init`

* create web project using maven archetype:

```
mvn archetype:generate -DgroupId=pl.wszib.kolo -DartifactId=tutorial-contacts-java -DarchetypeArtifactId=maven-archetype-webapp
```

## Step `1-mvc` Spring MVC

* add required dependencies into `pom.xml`
* update `web.xml`
* create `dispatcher-servlet.xml`
* create test
* create first controller with view

## Step `2-no-xml` Spring MVC without XML configuration

* remove all xml configurations
* add `tomcat-servlet-api` and `jstl` into your `pom.xml`
* create `WebConfig.java`, `WebInitializer.java`

## Step `3-hibernate`
* create database

```
CREATE DATABASE contacts CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE USER 'contacts'@'localhost' IDENTIFIED BY 'contacts123';
GRANT ALL ON contacts.* to contacts@localhost;
FLUSH PRIVILEGES;
```

* add `hibernate`, `mysql` dependencies into your `pom.xml`
* create `SpringConfiguration.java` with hibernate configuration
* create `model`, `dao` and `service`
* update `HomeController.java` and `home.jsp` to display contacts
* insert dumy data into database:

```
INSERT INTO Contact (email, firstname, lastname, telephone) VALUES ("foo@bar.pl", "foo", "bar", "+48345324534");
``` 
