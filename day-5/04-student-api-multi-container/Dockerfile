FROM openjdk:8-jdk

WORKDIR /usr/app

COPY target/student-api.jar /usr/app

EXPOSE 9090

CMD ["java","-jar","student-api.jar"]