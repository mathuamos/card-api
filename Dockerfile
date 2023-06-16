FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/card-api.jar
COPY ${JAR_FILE} card-api.jar
ENV TZ="Africa/Nairobi"
RUN date
ENTRYPOINT ["java","-jar","/card-api.jar"]