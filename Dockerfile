From java:8-jdk-alpine

COPY /target/GameShop-0.0.1-SNAPSHOT.jar /home/ubuntu/forDocker/

WORKDIR /home/ubuntu/forDocker

CMD["java","-jar","GameShop 0.0.1-SNAPSHOT.jar"]
