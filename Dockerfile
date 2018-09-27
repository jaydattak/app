FROM openjdk:8

ENV MYSQL_DATABASE=sba_db

ENV MYSQL_USER=root

ENV MYSQL_PASSWORD=pass@word1

ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/sba_db?useSSL=false

COPY . /usr/src

WORKDIR /usr/src

ADD ./target/App-0.0.1-SNAPSHOT.war /usr/src/App-0.0.1-SNAPSHOT.war

EXPOSE 8081

ENTRYPOINT ["java","-jar", "App-0.0.1-SNAPSHOT.war"]