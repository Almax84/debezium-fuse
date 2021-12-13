#FROM store/oracle/serverjre:1.8.0_241-b07
FROM  registry.redhat.io/openjdk/openjdk-11-rhel8
MAINTAINER dscrimie@redhat.com

USER root

RUN mkdir /opt/app-root && \
          useradd -G 0 -ms /bin/bash springboot
          
          

COPY ./target/kafka-mysql-0.0.1-SNAPSHOT.jar /opt/app-root

RUN chgrp -R 0 /opt/app-root && \
    chmod g=u /opt/app-root
    
#USER springboot

USER 1001



CMD ["java","-jar","/opt/app-root/kafka-mysql-0.0.1-SNAPSHOT.jar"]
