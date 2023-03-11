FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} customerms.jar

ENTRYPOINT [ "java","-jar", "./customerms.jar" ]

EXPOSE 3336