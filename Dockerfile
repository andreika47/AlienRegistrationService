FROM alpine:latest

ARG TOMCAT_VERSION=9.0.59

# Install packages
RUN apk update && \
    apk add openjdk11 && \
    apk add maven && \
    apk add nginx && \
    apk add apache2-utils && \
    apk add gettext

# Set up Nginx configuration
COPY nginx/nginx.conf /etc/nginx/
RUN mkdir -p /run/nginx

# Set up Tomcat
ENV CATALINA_HOME /usr/local/tomcat
ENV PATH $CATALINA_HOME/bin:$PATH
WORKDIR $CATALINA_HOME
RUN wget https://archive.apache.org/dist/tomcat/tomcat-9/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz && \
    tar -xzvf apache-tomcat-$TOMCAT_VERSION.tar.gz && \
    rm apache-tomcat-$TOMCAT_VERSION.tar.gz && \
    mv apache-tomcat-$TOMCAT_VERSION/* ./ && \
    rm -rf apache-tomcat-$TOMCAT_VERSION

COPY tomcat/context.xml $CATALINA_HOME/conf
COPY tomcat/tomcat-users.xml $CATALINA_HOME/conf

# Copy WAR file to Tomcat webapps directory
COPY target/AlienRegApp.war $CATALINA_HOME/webapps

# Expose ports
EXPOSE 8080

# Set up startup script
COPY startup.sh /
RUN chmod +x /startup.sh
CMD ["/startup.sh"]