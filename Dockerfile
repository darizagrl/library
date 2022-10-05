FROM openjdk:11-jre
ADD core/target/core-0.0.1-SNAPSHOT.jar core-0.0.1-SNAPSHOT.jar
#ADD data/books.mv.db books.mv.db
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "core-0.0.1-SNAPSHOT.jar"]