#!/bin/bash

#delete war file
# Set the path where you want to check for the WAR file
war_file_path="/tmp/codedeploy-deployment-staging-area/ecom-0.0.1-SNAPSHOT.war"

# Check if the WAR file exists
if [ -f "$war_file_path" ]; then
    sudo rm ecom-0.0.1-SNAPSHOT.war
else
    echo "WAR file not found at $war_file_path"
fi

# Install Java
sudo yum install java-17-amazon-corretto-devel -y

#Here The -y flag is used with package manager commands (like yum, apt-get, etc.) to automatically answer "yes" to any prompts during the installation process.