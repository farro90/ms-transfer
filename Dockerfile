FROM openjdk:11
VOLUME /tmp
EXPOSE 8115
ADD ./target/ms-transfer-0.0.1-SNAPSHOT.jar ms-transfer.jar
ENTRYPOINT ["java","-jar","ms-transfer.jar"]