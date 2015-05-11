#!/bin/sh

mvn clean && mvn install  -Dmaven.test.skip=true
echo "build success"
echo "sync $1"
case "${1:-''}" in 
'api')
echo "deploy SSO API"
scp -r api/target/sso-api*.war root@aliyun:~/api/apps/
echo "deploy SSO API Success!!!"
;;
'mgr')
echo "deploy SSO Manager"
scp -r manager/target/sso-manager*.war root@aliyun:~/mgr/apps/
echo "deploy SSO Manager Success!!!"
;;
esac

#scp -r api/target/sso-api*.war root@aliyun:~/api/apps/
#scp -r manager/target/sso-manager*.war root@aliyun:~/mgr/apps/

echo "deploy success"
