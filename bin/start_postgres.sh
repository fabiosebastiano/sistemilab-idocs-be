#!/usr/bin/env bash

set -euo pipefail
which docker > /dev/null || (echoerr "Please ensure that docker is in your PATH" && exit 1)

mkdir -p $HOME/NERDISH/progetti/dockerVolumes/postgres
rm -rf $HOME/dNERDISH/progetti/dockerVolumes/postgres/data

docker run --rm --name pg-sistemilab -e POSTGRES_USER=idocs_user -e POSTGRES_PASSWORD=postgres_pwd -e POSTGRES_DB=idocs -d -p 5432:5432 -v $HOME/NERDISH/progetti/dockerVolumes/postgres:/var/lib/postgresql postgres
sleep 3
export PGPASSWORD=postgres_pwd

docker cp ./schema.sql pg-sistemilab:/docker-entrypoint-initdb.d/schema.sql
docker cp ./data.sql pg-sistemilab:/docker-entrypoint-initdb.d/data.sql
docker exec -u postgres pg-sistemilab psql idocs idocs_user -f docker-entrypoint-initdb.d/schema.sql
docker exec -u postgres pg-sistemilab psql idocs idocs_user -f docker-entrypoint-initdb.d/data.sql
