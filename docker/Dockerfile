FROM openjdk:16-slim

ADD target/*.jar app.jar

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=fileS:/dev/./urandom -jar /app.jar"]
