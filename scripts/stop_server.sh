#!/bin/bash

# Find the process ID (PID) of the running Spring Boot application
SPRING_PID=$(ps -ef | grep "ecom-0.0.1-SNAPSHOT.war" | grep -v grep | awk '{print $2}')

# If the process is running, stop it
if [ -n "$SPRING_PID" ]; then
    sudo kill -15 $SPRING_PID
fi