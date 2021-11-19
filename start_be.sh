#!/usr/bin/env bash

#TODO: CHECK DEMONE DOCKER ED EVENTUALE AVVIO

cd bin && ./start_postgres.sh

cd .. && mvn compile quarkus:dev
