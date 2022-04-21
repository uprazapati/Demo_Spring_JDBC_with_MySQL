# Ride_Tracker_using_Spring_JDBC

Demo Project to show usage of Spring JDBC in a demo ride tracker app


# Things to do before starting the project

- Install MYSQL on your local system
- Install MYSQL workbench on on your local system to have a visual UI to check the tables
- Make sure to remeber your username and password set during installation
- Download Apache Tomcat 9.0.60 and extract it from compressed file. Store the obtained directory at a pth where you can remeber.
- Depending upon your IDE, you need to edit configuration template and add Tomcat Directory path for deployment purpose.
- Copy the content of createTables.sql file and execute it in SQL Workbench. This will create a required database schema
- Go to jdbc-config.xml file of your project and set corresponding username and password in the following field ```<property name="password" value="password" />```


# Repo Structure
The repo have multiple branches where in each branch a separate task is performed which can be understood from its branch. You should start from branch named **starter_branch** which is just a project start. The main branch contains the consolidated work. The project is compiled using Java 11 in IntelliJ IDE and based on Spring Framework. Maven is used to resolve dependencies.
src Directory - contains mvc structure of code
test Directory - contains tests for execution of REST API calls to perform DB operations


# Execution
Depending upon you IDE, run the tomcat server which will deploy your whole projects war file in its directory. The web server will listen on 127.0.0.1:8000. You can have a look at the rest controller in the source and correspondingly make the REST API calls through web where the home page is http://127.0.0.1:8080/ride_tracker. Or you can use the test module in the source to make calls and test it.
