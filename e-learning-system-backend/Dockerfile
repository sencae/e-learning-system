
FROM openjdk:10
EXPOSE 8080
ARG JAR_FILE=target/e-learning-system-backend-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} e-learning-system.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/e-learning-system.jar"]