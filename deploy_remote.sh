#!/bin/sh

cd ./dev/apache-tomcat-7.0.39

./bin/shutdown.sh
rm -rf webapps/homeautomation* work/Catalina/localhost/homeautomatio*
mv ~/homeautomation-*.war webapps/
sleep 5
kill -9 `ps auxwwww | grep java | grep tomcat | awk '{print $2}'`
sleep 5
./bin/startup.sh

