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

#### Configure jboss datasource (for dev and prod profiles)
Edit file *standalone/configuration/standolone.xml*.
```
<subsystem xmlns="urn:jboss:domain:datasources:5.0">
            <datasources>
                <datasource jndi-name="java:jboss/datasources/h2DS" pool-name="H2DS" enabled="true" use-java-context="true">
                    <connection-url>jdbc:h2:mem:dummydb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE</connection-url>
                    <driver>h2</driver>
                    <security>
                        <user-name>sa</user-name>
                        <password>password</password>
                    </security>
                </datasource>
                <drivers>
                    <driver name="h2" module="com.h2database.h2">
                        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
                    </driver>
                </drivers>
                <datasource jndi-name="java:jboss/datasources/ORACLE_DS" pool-name="ORACLE_DS" enabled="true" use-java-context="true">
                    <connection-url>jdbc:oracle:thin:@192.9.200.210:1521:oradesa</connection-url>
                    <driver>oracle</driver>
                    <security>
                        <user-name>AFPCONFIABE</user-name>
                        <password>AFPCONFIABE</password>
                    </security>
                </datasource>
                <drivers>
                    <driver name="oracle" module="com.oracle">
                      <driver-class>oracle.jdbc.driver.OracleDriver</driver-class>
                    </driver>
                </drivers>
            </datasources>
        </subsystem>
```
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

#### Run app without embedded Apache Tomcat server.
Copy .war file to *standalone/deployments/*.

#### Run app with embedded Apache Tomcat server.
```
$ make runProd
```
Run app with production environment.

```
$ make runDev
```
Run app with development environment.

### Testing (test profile).

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
