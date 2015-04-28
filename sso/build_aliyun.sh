#!/bin/sh

mvn clean && mvn install  -Dmaven.test.skip=true
echo "build success"
scp -r api/target/sso-api*.war root@aliyun:~/api/apps/
scp -r manager/target/sso-manager*.war root@aliyun:~/mgr/apps/
echo "deploy success"
