# selenium-springboot-cucumber-restassured - All in one using Aspect programming 

Log:
Execution logs are logged into execution.log under "target.exec.temp" as defined in application.properties 

Screenshot :

How to Run Tests
We can run the test in the command line with the maven command below. The below command is for the zhs terminal.

mvn -Dtest="com.kirti.springboot.tests.**" test
The command below is for the bash terminal.

mvn -Dtest=com.kirti.springboot.tests.** test
If we want to select a specific profile, we have to specify this as shown below.

mvn -Dtest="com.kirti.springboot.tests.**" -Dspring.profiles.active=grid test
For selenium grid execution, we should activate the selenium grid by running the Selenium Docker compose file.

docker-compose -f docker-compose-v3.yml up
