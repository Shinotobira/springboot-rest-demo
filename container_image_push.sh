#!/bin/sh

az login
az acr login --name tpbookregistryanthony

docker tag book tpbookregistryanthony.azurecr.io/book:latest

docker push tpbookregistryanthony.azurecr.io/book:latest