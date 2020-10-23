FROM openjdk:8
COPY ./target/eureks-server-*.jar eureks-server.jar
EXPOSE 8761
CMD ["java","-jar","-Dspring.profile.active=dev","eureks-server.jar"]
