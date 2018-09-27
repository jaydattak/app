FROM mysql:5.6.41

MAINTAINER me

ENV MYSQL_DATABASE=sba_db \

MYSQL_ROOT_PASSWORD=pass@word1

ADD sba_db.sql /docker-entrypoint-initdb.d

EXPOSE 3306