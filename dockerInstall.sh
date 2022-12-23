#!/bin/bash

export container=remoto-tfm

echo '#### Building application'
./gradlew clean build

echo '#### Creating image....'
docker image build --build-arg secret_key=${JASYPT_SECRET_KEY} -t $container:latest .

if [ ! "$(docker ps -q -f name=$container)" ]; then
  echo '#### Stopping previous container - '$container'...'
  docker stop $container
fi
if [ "$(docker ps -aq -f status=exited -f name=$container)" ]; then
  echo '#### Stopping previous container - '$container'...'
  docker rm $container
fi

echo '#### Creating new container - '$container'...'
docker run --name $container -d --restart unless-stopped -p8443:8443 $container:latest

#echo '#### Attaching stdout...'
#docker attach $container