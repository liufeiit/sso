#!/bin/bash

mvn clean && mvn eclipse:clean && mvn eclipse:eclipse -DdownloadSources=true

#for dir in $(ls ./)
#do
#    [ -d $dir ] && echo "deploy eclipse " $dir && cd $dir && mvn eclipse:eclipse -DdownloadSources=true && cd ../ && echo "deploy eclipse " $dir " success!!!"
#done
