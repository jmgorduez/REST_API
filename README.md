# ShoppingCart


### Problem background

We have an existing shopping cart application, with a small set of eCommerce/shopping cart rules. 
Rules include calculation of total price, discount and loyalty points calculation. 
Most of the business logic is implemented in single method `ShoppingCart.checkout`.
There is a `/products` endpoint to list the products. 

### Prerequisites

Java 1.8


### IDE Setup

#### IntelliJ IDEA

You should be able to import the project directly into IntelliJ after starting it.

* Select Import Project
* Navigate to project and select directory
* Select Gradle (in import project from external model)
* Make sure Gradle JVM is a Java 8 JDK
* Make sure ‘Use default gradle wrapper’ is selected

#### Eclipse

Make sure you have installed the Buildship plugin into Eclipse

* Go to Eclipse Marketplace (Help -> Eclipse Marketplace)
* Search from Buildship and install the plugin
* Restart Eclipse

After having the plugin installed you should now be able to you import the project into Eclipse

* File -> Import -> Gradle -> Gradle Existing Project
* Follow the wizard but making sure `gradle-wrapper` is selected

### Build locally

In your terminal run `./gradlew clean test build`

If this runs successfully we will have verified that your command line is setup correctly.

### Run locally

In your terminal run `./gradlew bootrun`. Then navigate to http://localhost:8080/v1/products in your browser to test the app is running.
