FROM openjdk:18-jdk-alpine3.14

COPY "./target/emsys.jar" "/application/emsys.jar"

CMD ["java", "-jar", "/application/emsys.jar"]