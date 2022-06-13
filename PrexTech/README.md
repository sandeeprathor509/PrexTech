# TestRunner #

A generic BDD test automation framework that supports WEB and rest assured.

### Getting Started ###

### Prerequisites

What you need to install before importing the project.

```
1. Java 11
2. Maven, version 3.6.3
```

### Plugins to be installed in Intellij

```
1. Cucumber for Java
2. Lombok
```

### To run your project###

1. Through maven `mvn clean install`
2. Alternatively, if user wants to run the website for admin then run `adminTestNG.xml`
and if a user wants to run the website for reward user then run `rewardTestNG.xml`
3. Other alternative, through command line user can run the following command in the terminal like
`mvn clean test -DsuiteXmlFile=adminTestNG.xml` or `mvn clean test -DsuiteXmlFile=rewardTestNG.xml`

### References

* lombok: https://www.baeldung.com/intro-to-project-lombok
* java-faker: https://www.baeldung.com/java-faker
* jackson-databind: https://www.baeldung.com/jackson-object-mapper-tutorial
* json-path: https://www.baeldung.com/guide-to-jayway-jsonpath
