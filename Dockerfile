FROM adoptopenjdk/openjdk11

WORKDIR /home/app

COPY target/demo-0.0.1-SNAPSHOT.jar /home/app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]