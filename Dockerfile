FROM openjdk:11
LABEL maintainer="heroes.az"
ADD target/heroes-2.5.6.jar heroes-2.5.6.jar
ENTRYPOINT ["java","-jar","heroes-2.5.6.jar"]