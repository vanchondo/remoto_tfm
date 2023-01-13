#!/bin/bash
IMAGE=remoto-tfm

echo '#### Building application'
./gradlew clean build

echo '#### Creating image....'
docker image build --build-arg secret_key=${JASYPT_SECRET_KEY} -t $IMAGE:latest .

echo '#### Uploading image to jfrog...'
docker tag $IMAGE vanchondo.jfrog.io/tfm/$IMAGE
docker push vanchondo.jfrog.io/tfm/$IMAGE

if [ ! "$(docker ps -q -f name=$IMAGE)" ]; then
  echo '#### Stopping previous container - '$IMAGE'...'
  docker stop $IMAGE
fi
if [ "$(docker ps -aq -f status=exited -f name=$IMAGE)" ]; then
  echo '#### Stopping previous container - '$IMAGE'...'
  docker rm $IMAGE
fi

echo '#### Creating new container - '$IMAGE'...'
docker run --name $IMAGE -d --restart unless-stopped -p8443:8443 $IMAGE:latest

#echo '#### Attaching stdout...'
#docker attach $IMAGE