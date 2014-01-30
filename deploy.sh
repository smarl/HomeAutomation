#!/bin/sh

mvn clean package

cd target
N=`ls *war`

scp $N ../deploy_remote.sh minime.local.:
ssh minime.local ./deploy_remote.sh

