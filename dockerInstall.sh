#!/bin/bash
docker image build --build-arg secret_key=${JASYPT_SECRET_KEY} -t remoto-tfm:latest .
docker stop remoto-tfm
docker rm remoto-tfm
docker run --name remoto-tfm -d --restart unless-stopped -p8080:8080 remoto-tfm:latest