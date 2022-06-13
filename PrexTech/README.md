# PrexTech Automation Framework #

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

### How to get the test Report###
Under the test-output, there is a file named as TestReport.html. For sampling adding a screenshot, which shows how it look like
<img width="1432" alt="image" src="https://user-images.githubusercontent.com/19299218/173318427-285d94f8-1f35-4061-86c2-37199002f2b2.png">


### References

* lombok: https://www.baeldung.com/intro-to-project-lombok
* java-faker: https://www.baeldung.com/java-faker
* jackson-databind: https://www.baeldung.com/jackson-object-mapper-tutorial
* json-path: https://www.baeldung.com/guide-to-jayway-jsonpath
