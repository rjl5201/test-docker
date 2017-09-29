FROM anapsix/alpine-java:jdk8

ENV installDir /opt/ryan/test-docker
ENV SERVICE_NAME testDocker
ENV SERVICE_VERSION 1

# install the application
RUN mkdir -p $installDir 
ADD HelloWorld.jar $installDir/HelloWorld.jar
# RUN echo 'hosts: files mdns4_minimal [NOTFOUND=return] dns mdns4' >> /etc/nsswitch.conf

WORKDIR $installDir

EXPOSE 6666

# command to run in the container when it starts up
ENTRYPOINT ["java", "-jar", "HelloWorld.jar"]
