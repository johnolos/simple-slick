#!/usr/bin/env bash

POSTGRES_VERSION="postgres:9.6"
CONTAINER_NAME="slick-postgres"

if ! [[ $(docker images -q $POSTGRES_VERSION) >/dev/null ]]; then
  printf "Pulling '%s' docker image.\n" "$POSTGRES_VERSION"
  docker pull $POSTGRES_VERSION
fi

if [[ $(docker ps -f name=$CONTAINER_NAME -f status=running -q) >/dev/null ]]; then
  printf "Stopping '%s' docker container.\n" "$CONTAINER_NAME"
  docker stop $CONTAINER_NAME >/dev/null
fi

if [[ $(docker ps -a -f name=$CONTAINER_NAME -f status=exited -q) >/dev/null ]]; then
  printf "Removing '%s' docker container.\n" "$CONTAINER_NAME"
  docker container rm $CONTAINER_NAME >/dev/null
fi

printf "Starting '%s' docker container.\n" "$CONTAINER_NAME"
docker run --name slick-postgres -p 127.0.0.1:5432:5432 -e POSTGRES_USER=slick -e POSTGRES_PASSWORD=slick -d postgres >/dev/null
