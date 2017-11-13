# Sample project responsible to traverse graph in a certain format

## How to build this project?
Pre-req
* Install Git
* Install Java
* Install Maven

```
git clone https://github.com/ankcha/projects
cd sample-project
mvn clean install
```

## How to run this project
```
java -cp target/dependency-graph-1.0-SNAPSHOT.jar com.graph.dependencies.App $LOCATION_OF_GRAPH.TXT
```

## How to build this project via Jenkins
* Configure Git credentials. Example: jenkins-url/credentials
* Configure Maven as part of Global Tools Configuration and set the name to M3
* Create a Jenkins Pipeline job, use [Jenkinsfile](https://github.com/ankcha/projects/blob/master/sample-project/Jenkinsfile). 
* Create and populate CREDENTIAL_ID parameter as part of this build job.
* Run the newly created job
