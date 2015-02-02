#!/bin/sh

mvn clean && mvn install  -Dmaven.test.skip=true
echo "build success"
scp -r api/target/sso-api*.war root@aliyun:~/jetty/app/
echo "deploy success"
