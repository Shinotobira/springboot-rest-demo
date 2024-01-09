#!/bin/sh

az login
az acr login --name tpbookregistryanthony

docker pull tpbookregistryanthony.azurecr.io/book:latest
docker run -d -p 8080:8080 tpbookregistryanthony.azurecr.io/book:latest
