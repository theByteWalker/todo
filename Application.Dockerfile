FROM maven:3.3-jdk-8

WORKDIR /usr/src/app

COPY . .

EXPOSE 8080

RUN unset MAVEN_CONFIG

RUN ./mvnw spring-boot:run