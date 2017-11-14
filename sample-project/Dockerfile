FROM openjdk:9.0.1-11-jre-slim
LABEL maintainer ankushchadha@gmail.com

COPY target/*.jar /usr/app/
COPY entrypoint.sh /usr/app/
RUN mkdir /usr/app/data
RUN chmod 700 /usr/app/entrypoint.sh

CMD /usr/app/entrypoint.sh

