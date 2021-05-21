FROM maven:3.6.3-jdk-8

ARG GIT_ACCESS_NAME
ARG GIT_ACCESS_TOKEN
ARG BRANCH=main

RUN apt-get update
RUN apt-get install git
RUN git --version

RUN mkdir -p /gatling-test

RUN git clone -b $BRANCH https://$GIT_ACCESS_NAME:$GIT_ACCESS_TOKEN@github.com/coolexplorer/maven-gatling.git ./gatling-test
WORKDIR /gatling-test

RUN mvn -s /usr/share/maven/ref/settings-docker.xml clean gatling:test

RUN mvn clean

CMD ["mvn", "-o", "gatling:test"]