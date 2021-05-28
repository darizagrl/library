FROM openjdk:11-jre
ADD core/target/librarycore.jar librarycore.jar
#ADD data/books.mv.db books.mv.db
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "librarycore.jar"]