FROM openjdk:10
EXPOSE 8080 8080
ARG JAR_FILE=e-learning-system-backend/target/e-learning-system-backend-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} e-learning-system.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/e-learning-system.jar"]

FROM postgres:11
EXPOSE 5432 5432
ENV POSTGRES_DB=e_learning_system
ENV POSTGRES_PASSWORD=123
COPY db/script/ /docker-entrypoint-initdb.d/
