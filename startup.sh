#!/bin/sh

# Generate Nginx configuration
envsubst < /etc/nginx/tomcat.template > /etc/nginx/conf.d/tomcat.conf

# Start Nginx
nginx -g "daemon off;" &

# Start Tomcat
catalina.sh run