FROM postgres:11
EXPOSE 5432
ENV POSTGRES_DB=e_learning_system
ENV POSTGRES_PASSWORD=123
COPY ./script/ /docker-entrypoint-initdb.d/
