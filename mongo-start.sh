#!/bin/sh

./mongo-stop.sh
docker run -d -p 27017:27017 --name=mongo mongo:latest