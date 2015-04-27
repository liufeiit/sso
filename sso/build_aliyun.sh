#!/bin/sh

mvn clean && mvn install  -Dmaven.test.skip=true
echo "build success"
scp -r api/target/sso-api*.war manager/target/sso-manager*.war root@aliyun:~/jetty/apps/
echo "deploy success"
