#!/bin/bash
VAR=""
cd /home/ceirapp/ceir/PostMan/EmailService
status=`ps -ef | grep CEIRPostman-0.0.8-SNAPSHOT_EmailService.ja | grep java`
if [ "$status" != "$VAR" ]
then
 echo "nothing"
 echo $status
else
 echo "to start"
java -jar CEIRPostman-0.0.8-SNAPSHOT_EmailService.jar   -Dspring.config.location=:./application.properties -Dlogback.configurationFile=:./logback.xml  1>/home/ceirapp/ceir/PostMan/EmailService/logs/log.log 2>/home/ceirapp/ceir/PostMan/EmailService/logs/log.log  &

fi