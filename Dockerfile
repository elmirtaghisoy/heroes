FROM openjdk:11
LABEL maintainer="heroes.az"
ENV TZ=Asia/Baku
VOLUME /media
WORKDIR /app
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY target/heroes-2.5.6.jar heroes-2.5.6.jar
ENTRYPOINT ["java","-jar","heroes-2.5.6.jar"]
CMD java -jar heroes-2.5.6.jar