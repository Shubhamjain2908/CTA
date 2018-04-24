# CTA
 A Coin Tracking Application that allows people to signup/login and mark their favorite cryptocurrency and view its daily status. 
 
# Installation
 Prerequisites:

 - mysql, maven, jdk 8 

# Build

- tweek any properties in the resources/application.properties files. Add database credentials.
- Create a single table using "user.sql" under resources folder

# Run as
 - mvn clean compile install 

# Instructions to run as spring boot project

- Import project in eclipse as existing maven project
- Run as maven project with command "mvn clean compile install"
- Modify application.properties to refer your configuration
- Locate executable JAR file (CTA.jar) inside /target folder
- Run command "Java -jar CTA.jar (This will launch inbuilt tomcat container and execute the application)
