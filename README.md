# e-learning-system

## To build and run application
From the top level directory of project, run: 
```
mvn clean install
cd e-learning-system-backend
mvn spring-boot:run
```
## To compile the parts separately
### For backend
You can use build project
```
cd e-learning-system-backend
mvn compile
```
### For frontend
```
cd e-learning-system-frontend/src/main/frontend
ng build
```

if you have already done `mvn clean install` it will speed up the launch of the project.
You will only need to perform `mvn spring-boot:run`