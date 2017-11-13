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
java -cp target/dependency-graph-1.0-SNAPSHOT.jar com.graph.dependencies.App
```

## How to build this project via Jenkins
* Configure Git credentials. Example: jenkins-url/credentials
* Create a Jenkins Pipeline job, use [Jenkinsfile](https://github.com/ankcha/projects/blob/master/sample-project/Jenkinsfile). Create and populate CREDENTIAL_ID parameter as part of this build job.
