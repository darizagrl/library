FROM openjdk:15-jdk-alpine
ADD target/demo-books.jar demo-books.jar
ADD data/books.mv.db books.mv.db
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "demo-books.jar"]