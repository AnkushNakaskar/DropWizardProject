# DropWizardProject
* Enviroment Commonds are the one which you provide at the run time of jar , like project
    ``` java -jar FirstDropWizardPRoject-1.0-SNAPSHOT.jar server application.yml```
* This project explain about the zookeeper instance registration and discovery 
    * if you see the BrandController ,There is discovery client configuration , it checks for other-server in zookeeper
    * It does not have load balancing , it will give the any instance from zookeeper
    * I tried registrating  two instances of other-service, getting only one instance in discovery client.
    
* next step would be server side load balancing using dropwizard.    
    