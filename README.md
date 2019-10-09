# Confía REST API. 
### Run indications.

#### Run app.
```
$ make runProd
```
Run app with production environment.

```
$ make runDev
```
Run app with development environment.

#### Refresh gradle dependence
```
$ make refresh
```
#### Generate .jar file.
Edit build.gradle comment line: *****exclude group: 'org.springframework.boot', module: 'spring-boot-starter-tomcat'*****
```
$ make jar
```
#### Generate .war file.
Edit build.gradle uncomment line: *****exclude group: 'org.springframework.boot', module: 'spring-boot-starter-tomcat'*****
```
$ make war
```
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
