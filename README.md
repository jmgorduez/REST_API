# Confía REST API. 
### Dependencies
  - IntelliJ IDEA.
  - gradle.
  - git.
  - GNU Make.
  
### Steps to install using IntelliJ IDEA.
  - Define maven config from: Preferences | Build, Execution, Deployment | Build Tools | Maven.
  - Install IntelliJ IDEA's plugin for lombok from: File | Settings | Plugins.
  - Restart IntelliJ IDEA.
  - Activate the checkbox *Enable annotation processing* from: Preferences | Build, Execution, Deployment | Compiler | Annotation Processors.
  - Install ojdbc7.jar into your maven local repo, *following this document: https://www.mkyong.com/maven/how-to-add-oracle-jdbc-driver-in-your-maven-local-repository/*.

### Run app indications.

#### Refresh gradle dependence
```
$ make refresh
```
#### Generate .jar file to deploy on embedded Apache Tomcat server.
Edit build.gradle comment line: *****exclude group: 'org.springframework.boot', module: 'spring-boot-starter-tomcat'*****
```
$ make jar
```
#### Generate .war file to deploy on not embedded app server.
Edit build.gradle uncomment line: *****exclude group: 'org.springframework.boot', module: 'spring-boot-starter-tomcat'*****
```
$ make war
```

#### Run app with embedded Apache Tomcat server.
```
$ make runProd
```
Run app with production environment.

```
$ make runDev
```
Run app with development environment.

### Testing.

#### Run integration tests.
```
$ make test
```
The result of unit tests will be available under: *build/reports/tests/test/*

#### Run test coverage.
```
$ make coverage
```
The result of tests coverage will be available under: *build/reports/jacoco/test/html/*
