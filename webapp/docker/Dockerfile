FROM centos:centos7

RUN yum install -y java-1.8.0-openjdk tar

ENV TOMCAT_MIRROR  http://mirror.lagoon.nc/pub/apache/tomcat
ENV TOMCAT_VERSION 8.0.36

WORKDIR /opt
RUN curl -s $TOMCAT_MIRROR/tomcat-${TOMCAT_VERSION%%.*}/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz |tar zxv
RUN ln -s apache-tomcat-$TOMCAT_VERSION tomcat

USER root

RUN ln -s /opt/tomcat/webapps /

ADD lib/postgresql-9.4.1208.jar /opt/tomcat/lib/
ADD conf/context.xml \
    conf/tomcat-users.xml \
    /opt/tomcat/conf/

WORKDIR /opt/tomcat
CMD bin/catalina.sh run
ENV CATALINA_OPTS -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.port=9090 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false -Djava.awt.headless=true -XX:+UseConcMarkSweepGC -Xmx256m

RUN rm -fr webapps/*
ADD ROOT /opt/tomcat/webapps/ROOT
