# new-kitchensink

It is Migrated Spring Boot application.

To deploy the Kitchen Sink application (a Spring Boot application) locally, follow these steps:

Prerequisites
-------------
1) Java Development Kit (JDK): Ensure you have JDK 21 installed. You can check your Java version by running:   java -version
2) Maven: Ensure Apache Maven is installed. Check your Maven installation with: mvn -version
3) MongoDB: Make sure MongoDB is installed and running locally.

Steps to Deploy the Application Locally
---------------------------------------

Step 1:
Clone the Repository: Clone the application from Git repository to your local machine:
git clone https://github.com/your-username/kitchensink.git
cd kitchensink

Step 2:
Configure application.properties: Open src/main/resources/application.properties and configure your MongoDB connection string: spring.data.mongodb.uri=mongodb://localhost:27017/yourdbname

Step 3:
Build the Application: Use Maven to build the application. This will compile the code and package it into a JAR file: mvn clean package

Step 4:
Run the Application: After building the application run it from your local IDE or by using below comment: java -jar target/kitchensink-0.0.1-SNAPSHOT.jar
Adjust the JAR file name based on your project configuration.

Step 5:
Access the Application: Once the application is running, open your web browser and go to:
http://localhost:8080/kitchensink/index.html
Replace 8080 with the port number configured in your application if it's different.

Run the Application:
-------------------
If you're using an IDE like IntelliJ or Eclipse, you can also run the application directly from there by running the main method in your KitchenSinkLoaderApplication class.


Testing the Application
-----------------------
Register Members: Use the registration form to add members to the database.

View Members: After registering members, you should be able to see them listed on the page.

Troubleshooting
---------------

MongoDB Connection Issues: Ensure MongoDB is running and accessible at the specified URI.
Port Conflicts: If the application doesn’t start due to a port conflict, you can change the server port in application.properties: server.port=8081